package com.example.woonho.bitflowsimulator.fragment

import android.annotation.TargetApi
import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.example.woonho.bitflowsimulator.CalculatorUtils
import com.example.woonho.bitflowsimulator.PreferenceConstants
import com.example.woonho.bitflowsimulator.PreferenceHelper
import com.example.woonho.bitflowsimulator.R

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.based_transaction_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun initView(v: View) {

        miningTurnTransaction = v.findViewById(R.id.edit_mining_transaction)
        refillTurnTransaction = v.findViewById(R.id.edit_refill_transaction)

        resultCommission = v.findViewById(R.id.result_mining_commission)
        resultExchangeCommission = v.findViewById(R.id.result_mining_trader_commission)
        resultUserCommission = v.findViewById(R.id.result_mining_user_commission)

        resultRefillCommission = v.findViewById(R.id.result_refill_commission)
        resultRefillExchangeCommission = v.findViewById(R.id.result_refill_trader_commission)
        resultRefillUserCommission = v.findViewById(R.id.result_refill_user_commission)
        resultBFTBuying = v.findViewById(R.id.result_refill_bft_buy)

        btnCalculation = v.findViewById(R.id.result_btn_container)
        btnCalculation.setOnClickListener {
            computeAndSetResult()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun computeAndSetResult() {

        if (!TextUtils.isEmpty(miningTurnTransaction.text)) {
            val miningTurnTransaction = miningTurnTransaction.text.toString()
            val compute = CalculatorUtils(context)
            val commission = compute.computeMiningTurnCommission(miningTurnTransaction.toFloat())
            resultCommission.text = commission.toString()

            val exchangeDividend = compute.computeMiningTurnExchangeDividend(commission)
            resultExchangeCommission.text = exchangeDividend.toString()

            val userDividend = compute.computeMiningTurnUserDividend(commission)
            resultUserCommission.text = userDividend.toString()
        }

        if (!TextUtils.isEmpty(refillTurnTransaction.text)) {
            val refillTurnTransaction = refillTurnTransaction.text.toString()
            val compute = CalculatorUtils(context)
            val commission = compute.computeMiningTurnCommission(refillTurnTransaction.toFloat())
            resultRefillCommission.text = commission.toString()

            val exchangeDividend = compute.computeRefillTurnExchangeDividend(commission)
            resultRefillExchangeCommission.text = exchangeDividend.toString()

            val userDividend = compute.computeRefillTurnUserDividend(commission)
            resultRefillUserCommission.text = userDividend.toString()

            val buyingBFT = compute.computeRefillTurnBFTBuying(commission)
            resultBFTBuying.text = buyingBFT.toString()
        }

    }

}