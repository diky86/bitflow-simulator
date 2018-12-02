package com.example.woonho.osnbit.fragment

import android.annotation.TargetApi
import android.app.Fragment
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import com.example.woonho.bitflowsimulator.PreferenceConstants
import com.example.woonho.bitflowsimulator.PreferenceHelper
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

    private lateinit var editUserCommission: EditText
    private lateinit var editUserCount: EditText

    // Tab
    private lateinit var btnBasedTurn: TextView
    private lateinit var btnBasedTransaction: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView(view)
    }

    @TargetApi(Build.VERSION_CODES.M)
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

        editUserCommission = v.findViewById(R.id.edit_user_commission)
        editUserCommission.setText(
                PreferenceHelper.getInstance(context).getLongExtra(PreferenceConstants.USER_DIVIDEND).toString())

//        editUserCommission.setOnEditorActionListener(TextView.OnEditorActionListener { texView, id, keyEvent ->
//            if (id == EditorInfo.IME_ACTION_DONE /*||
//                    keyEvent.action == KeyEvent.KEYCODE_ENTER*/) {
//
//                Log.d(TAG, "woonho user dividend = " + texView.text)
//
////                PreferenceHelper.getInstance(context).putLongExtra(PreferenceConstants.USER_DIVIDEND, texView.text as Long)
//                return@OnEditorActionListener true
//            }
//            false
//        })


        editUserCount = v.findViewById(R.id.edit_user_count)

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