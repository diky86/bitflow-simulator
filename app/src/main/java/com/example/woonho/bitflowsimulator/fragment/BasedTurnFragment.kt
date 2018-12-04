package com.example.woonho.osnbit.fragment

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

    private lateinit var editBFTPrice: EditText
    private lateinit var editBFTCount: EditText
    private lateinit var editUserCount: EditText
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

    private fun initView(v: View) {

        editBFTPrice = v.findViewById(R.id.edit_bft_price)
        editBFTCount = v.findViewById(R.id.edit_bft_count)
        editUserCount = v.findViewById(R.id.edit_user_count)
        editTurnCount = v.findViewById(R.id.edit_turn_count)

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

        if (!TextUtils.isEmpty(editBFTPrice.text) &&
                !TextUtils.isEmpty(editBFTCount.text)) {
            val bftPrice = editBFTPrice.text.toString()
            val bftCount = editBFTCount.text.toString()
            val compute = CalculatorUtils(context)
            val tradedPrice = compute.computeTurnStandardTradePrice(bftPrice.toFloat(), bftCount.toFloat())
            resultTradedPrice.text = tradedPrice.toString()

            val tradedCommission = compute.computeTurnStandardTradedCommision(tradedPrice)
            resultTradedCommission.text = tradedCommission.toString()

            if (!TextUtils.isEmpty(editTurnCount.text)) {
                val turnCount = editTurnCount.text.toString()
                val exchangeRevenue = compute.computeTurnStandardExchangeRevenue(tradedCommission) * turnCount.toFloat()
                resultExchangeRevenue.text = exchangeRevenue.toString()


            }
        }


    }
}