package com.example.woonho.osnbit.fragment

import android.app.Fragment
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.woonho.bitflowsimulator.R
import com.example.woonho.bitflowsimulator.fragment.BasedTransactionFragment

class MainTabFragment : Fragment() {
    private val TAG = "MainTabFragment"

    companion object {
        fun newInstance(): Fragment {
            MainTabFragment().run {
                return this
            }
        }
    }

    private lateinit var btnBasedTurn: TextView
    private lateinit var btnBasedTransaction: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    private fun initView(v: View) {

        val fm = fragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_layout, BasedTurnFragment.newInstance())
        ft.commit()

        // 턴 기준
        btnBasedTurn = v.findViewById(R.id.btn_based_turn) as TextView
        btnBasedTurn.tag = 0
        btnBasedTurn.setOnClickListener {
            setTextBold(btnBasedTurn)
            val fm = fragmentManager
            val ft = fm.beginTransaction()
            ft.replace(R.id.fragment_layout, BasedTurnFragment.newInstance())
            ft.commit()
        }

        // 거래금액 기준
        btnBasedTransaction = v.findViewById(R.id.btn_based_transaction) as TextView
        btnBasedTransaction.tag = 1
        btnBasedTransaction.setOnClickListener {
            setTextBold(btnBasedTransaction)
            val fm = fragmentManager
            val ft = fm.beginTransaction()
            ft.replace(R.id.fragment_layout, BasedTransactionFragment.newInstance())
            ft.commit()
        }


    }

    private fun setTextBold(view: TextView) {

        val viewGroup = arrayOf(btnBasedTurn, btnBasedTransaction)
        for (v in viewGroup) {
            if (v.tag == view.tag) {
                v.setTypeface(view.typeface, Typeface.BOLD)
                v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
            } else {
                v.typeface = Typeface.DEFAULT
                v.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            }
        }
    }

}