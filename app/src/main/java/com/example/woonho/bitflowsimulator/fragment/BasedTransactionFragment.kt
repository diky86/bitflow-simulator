package com.example.woonho.bitflowsimulator.fragment

import android.annotation.TargetApi
import android.app.Fragment
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.example.woonho.bitflowsimulator.CalculatorUtils
import com.example.woonho.bitflowsimulator.R
import java.text.DecimalFormat

class BasedTransactionFragment : Fragment() {
    private val TAG = "BasedTurnFragment"

    companion object {
        fun newInstance(): Fragment {
            BasedTransactionFragment().run {
                return this
            }
        }
    }

    private lateinit var miningTurnTransaction: EditText
    private lateinit var refillTurnTransaction: EditText
    private lateinit var btnCalculation: View

    private lateinit var resultCommission: TextView
    private lateinit var resultExchangeCommission: TextView
    private lateinit var resultUserCommission: TextView

    private lateinit var resultRefillCommission: TextView
    private lateinit var resultRefillExchangeCommission: TextView
    private lateinit var resultRefillUserCommission: TextView
    private lateinit var resultBFTBuying: TextView

    private lateinit var totalResultExchangeRevenue: TextView
    private lateinit var totalResultUserRevenue: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.based_transaction_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun initView(v: View) {

        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        miningTurnTransaction = v.findViewById(R.id.edit_mining_transaction)
        miningTurnTransaction.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Long
                val bftCount = miningTurnTransaction.text.toString()
                if (!TextUtils.isEmpty(bftCount)) {
                    value = bftCount.toLong()
                } else {
                    value = 0
                }
                miningTurnTransaction.setText(value.toString())
            } else {
                imm.showSoftInput(miningTurnTransaction, 0)
                miningTurnTransaction.setText("")
            }
        }
        miningTurnTransaction.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                miningTurnTransaction.clearFocus()
                imm.hideSoftInputFromWindow(miningTurnTransaction.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        refillTurnTransaction = v.findViewById(R.id.edit_refill_transaction)
        refillTurnTransaction.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Long
                val bftCount = refillTurnTransaction.text.toString()
                if (!TextUtils.isEmpty(bftCount)) {
                    value = bftCount.toLong()
                } else {
                    value = 0
                }
                refillTurnTransaction.setText(value.toString())
            } else {
                imm.showSoftInput(refillTurnTransaction, 0)
                refillTurnTransaction.setText("")
            }
        }
        refillTurnTransaction.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                refillTurnTransaction.clearFocus()
                imm.hideSoftInputFromWindow(refillTurnTransaction.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        resultCommission = v.findViewById(R.id.result_mining_commission)
        resultExchangeCommission = v.findViewById(R.id.result_mining_trader_commission)
        resultUserCommission = v.findViewById(R.id.result_mining_user_commission)

        resultRefillCommission = v.findViewById(R.id.result_refill_commission)
        resultRefillExchangeCommission = v.findViewById(R.id.result_refill_trader_commission)
        resultRefillUserCommission = v.findViewById(R.id.result_refill_user_commission)
        resultBFTBuying = v.findViewById(R.id.result_refill_bft_buy)

        totalResultExchangeRevenue = v.findViewById(R.id.result_trader_revenue)
        totalResultUserRevenue = v.findViewById(R.id.result_user_revenue)

        btnCalculation = v.findViewById(R.id.result_btn_container)
        btnCalculation.setOnClickListener {
            computeAndSetResult()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun computeAndSetResult() {

        var miningTurnExchangeDividend = 0f
        var miningTurnUserDividend = 0f

        val compute = CalculatorUtils(context)
        if (!TextUtils.isEmpty(miningTurnTransaction.text)) {
            val miningTurnTransaction = miningTurnTransaction.text.toString()
            val commission = compute.computeMiningTurnCommission(miningTurnTransaction.toFloat())
            resultCommission.text = commission.toString()

            miningTurnExchangeDividend = compute.computeMiningTurnExchangeDividend(commission)
            resultExchangeCommission.text = miningTurnExchangeDividend.toString()

            miningTurnUserDividend = compute.computeMiningTurnUserDividend(commission)
            resultUserCommission.text = miningTurnUserDividend.toString()
        }

        var refillTurnExchangeDividend = 0f
        var refillTurnUserDividend = 0f

        if (!TextUtils.isEmpty(refillTurnTransaction.text)) {
            val refillTurnTransaction = refillTurnTransaction.text.toString()
            val commission = compute.computeMiningTurnCommission(refillTurnTransaction.toFloat())
            resultRefillCommission.text = commission.toString()

            refillTurnExchangeDividend = compute.computeRefillTurnExchangeDividend(commission)
            resultRefillExchangeCommission.text = refillTurnExchangeDividend.toString()

            refillTurnUserDividend = compute.computeRefillTurnUserDividend(commission)
            resultRefillUserCommission.text = refillTurnUserDividend.toString()

            val buyingBFT = compute.computeRefillTurnBFTBuyingCount(refillTurnTransaction.toFloat())
            resultBFTBuying.text = buyingBFT.toString()
        }

        val dcf = DecimalFormat("###,###.##")
        val totalResultExchange = dcf.format(miningTurnExchangeDividend + refillTurnExchangeDividend)
        val totalResultUser = dcf.format(miningTurnUserDividend + refillTurnUserDividend)

        totalResultExchangeRevenue.text = totalResultExchange
        totalResultUserRevenue.text = totalResultUser
    }
}