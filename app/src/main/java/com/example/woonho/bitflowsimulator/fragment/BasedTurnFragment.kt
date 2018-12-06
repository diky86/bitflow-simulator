package com.example.woonho.osnbit.fragment

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
import com.example.woonho.bitflowsimulator.PreferenceConstants
import com.example.woonho.bitflowsimulator.PreferenceHelper
import com.example.woonho.bitflowsimulator.R

class BasedTurnFragment : Fragment() {
    private val TAG = "BasedTurnFragment"

    companion object {
        fun newInstance(): Fragment {
            BasedTurnFragment().run {
                return this
            }
        }
    }

    private lateinit var editBFTCount: EditText
    private lateinit var editTurnCount: EditText

    private lateinit var resultTradedPrice: TextView
    private lateinit var resultTradedCommission: TextView

    private lateinit var resultExchangeRevenue: TextView
    private lateinit var resultUserRevenue: TextView

    private lateinit var btnCompute: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.based_turn_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun initView(v: View) {

        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        editBFTCount = v.findViewById(R.id.edit_bft_count)
        editBFTCount.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Int
                val bftCount = editBFTCount.text.toString()
                if (!TextUtils.isEmpty(bftCount)) {
                    value = bftCount.toInt()
                } else {
                    value = 0
                }
                editBFTCount.setText(value.toString())
            } else {
                imm.showSoftInput(editBFTCount, 0)
                editBFTCount.setText("")
            }
        }
        editBFTCount.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editBFTCount.clearFocus()
                imm.hideSoftInputFromWindow(editBFTCount.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        editTurnCount = v.findViewById(R.id.edit_turn_count)
        editTurnCount.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Int
                val bftCount = editTurnCount.text.toString()
                if (!TextUtils.isEmpty(bftCount)) {
                    value = bftCount.toInt()
                } else {
                    value = 0
                }
                editTurnCount.setText(value.toString())
            } else {
                imm.showSoftInput(editTurnCount, 0)
                editTurnCount.setText("")
            }
        }
        editTurnCount.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editTurnCount.clearFocus()
                imm.hideSoftInputFromWindow(editTurnCount.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        resultTradedPrice = v.findViewById(R.id.result_trade_price)
        resultTradedCommission = v.findViewById(R.id.result_trade_commission)

        resultExchangeRevenue = v.findViewById(R.id.result_trader_revenue)
        resultUserRevenue = v.findViewById(R.id.result_user_revenue)

        btnCompute = v.findViewById(R.id.result_btn_container)
        btnCompute.setOnClickListener {
            computeAndSetResult()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun computeAndSetResult() {

        if (!TextUtils.isEmpty(editBFTCount.text)) {
            val bftPrice = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.BFT_PRICE)
            val bftCount = editBFTCount.text.toString()
            val compute = CalculatorUtils(context)

            if (!TextUtils.isEmpty(editTurnCount.text)) {
                val turnCount = editTurnCount.text.toString()

                val tradedPrice = compute.computeTurnStandardTradePrice(bftPrice, bftCount.toFloat()) * turnCount.toFloat()
                resultTradedPrice.text = (tradedPrice * 2).toString()

                val tradedCommission = compute.computeTurnStandardTradedCommision(tradedPrice) * turnCount.toFloat()
                resultTradedCommission.text = (tradedCommission * 2).toString()

                val exchangeRevenue = compute.computeTurnStandardExchangeRevenue(tradedCommission) * turnCount.toFloat()
                resultExchangeRevenue.text = exchangeRevenue.toString()

                val userRevenue = compute.computeTurnStandardUserRevenue(tradedCommission) * turnCount.toFloat()
                resultUserRevenue.text = userRevenue.toString()

            }
        }


    }
}