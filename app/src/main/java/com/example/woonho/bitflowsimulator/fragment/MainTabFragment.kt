package com.example.woonho.osnbit.fragment

import android.annotation.TargetApi
import android.app.Fragment
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
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
    private lateinit var editTraderCommission: EditText
    private lateinit var editCommission: EditText
    private lateinit var editBtcCommission: EditText
    private lateinit var editUserCount: EditText

    private lateinit var editGrade1: EditText
    private lateinit var editGrade2: EditText
    private lateinit var editGrade3: EditText
    private lateinit var editGrade4: EditText
    private lateinit var editGrade5: EditText
    private lateinit var editGrade6: EditText
    private lateinit var editGrade7: EditText
    private lateinit var editGrade8: EditText
    private lateinit var editGrade9: EditText
    private lateinit var editGrade10: EditText
    private lateinit var editGrade11: EditText

    private lateinit var btnSetting: View

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
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_DIVIDEND).toString())

        editTraderCommission = v.findViewById(R.id.edit_trader_commission)
        editTraderCommission.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND).toString())

        editCommission = v.findViewById(R.id.edit_commission)
        editCommission.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.TRADE_COMMISSION).toString()
        )

        editBtcCommission = v.findViewById(R.id.edit_btc_commission)
        editBtcCommission.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION).toString()
        )

        editUserCount = v.findViewById(R.id.edit_user_count)
        editUserCount.setText(
                PreferenceHelper.getInstance(context).getIntExtra(PreferenceConstants.USER_COUNT).toString()
        )

        editGrade1 = v.findViewById(R.id.edit_grade_1)
        editGrade1.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_1GRADE_PERCENT).toString())

        editGrade2 = v.findViewById(R.id.edit_grade_2)
        editGrade2.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_2GRADE_PERCENT).toString())

        editGrade3 = v.findViewById(R.id.edit_grade_3)
        editGrade3.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_3GRADE_PERCENT).toString())

        editGrade4 = v.findViewById(R.id.edit_grade_4)
        editGrade4.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_4GRADE_PERCENT).toString())

        editGrade5 = v.findViewById(R.id.edit_grade_5)
        editGrade5.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_5GRADE_PERCENT).toString())

        editGrade6 = v.findViewById(R.id.edit_grade_6)
        editGrade6.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_6GRADE_PERCENT).toString())

        editGrade7 = v.findViewById(R.id.edit_grade_7)
        editGrade7.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_7GRADE_PERCENT).toString())

        editGrade8 = v.findViewById(R.id.edit_grade_8)
        editGrade8.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_8GRADE_PERCENT).toString())

        editGrade9 = v.findViewById(R.id.edit_grade_9)
        editGrade9.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_9GRADE_PERCENT).toString())

        editGrade10 = v.findViewById(R.id.edit_grade_10)
        editGrade10.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_10GRADE_PERCENT).toString())

        editGrade11 = v.findViewById(R.id.edit_grade_11)
        editGrade11.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_11GRADE_PERCENT).toString())

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

        btnSetting = v.findViewById(R.id.btn_setting)
        btnSetting.setOnClickListener {
            saveSettingValue()
        }
    }

    private fun saveSettingValue() {
        // 유저 배당
        if (!TextUtils.isEmpty(editUserCommission.text)) {
            val userCommission = editUserCommission.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_DIVIDEND, userCommission.toFloat())
        }

        // 거래소 배당
        if (!TextUtils.isEmpty(editTraderCommission.text)) {
            val exchangeCommission = editTraderCommission.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND, exchangeCommission.toFloat())
        }

        // 수수료
        if (!TextUtils.isEmpty(editCommission.text)) {
            val commisiion = editCommission.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.TRADE_COMMISSION, commisiion.toFloat())
        }

        // BTC
        if (!TextUtils.isEmpty(editBtcCommission.text)) {
            val btcCommission = editBtcCommission.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION, btcCommission.toFloat())
        }

        // 유저
        if (!TextUtils.isEmpty(editUserCount.text)) {
            val userCount = editUserCount.text.toString()
            PreferenceHelper.getInstance(context).putIntExtra(PreferenceConstants.USER_COUNT, userCount.toInt())
        }

        // 등급
        if (!TextUtils.isEmpty(editGrade1.text)) {
            val grade1 = editGrade1.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_1GRADE_PERCENT, grade1.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade2.text)) {
            val grade2 = editGrade2.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_2GRADE_PERCENT, grade2.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade3.text)) {
            val grade3 = editGrade3.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_3GRADE_PERCENT, grade3.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade4.text)) {
            val grade4 = editGrade4.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_4GRADE_PERCENT, grade4.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade5.text)) {
            val grade5 = editGrade5.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_5GRADE_PERCENT, grade5.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade6.text)) {
            val grade6 = editGrade6.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_6GRADE_PERCENT, grade6.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade7.text)) {
            val grade7 = editGrade7.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_7GRADE_PERCENT, grade7.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade8.text)) {
            val grade8 = editGrade8.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_8GRADE_PERCENT, grade8.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade9.text)) {
            val grade9 = editGrade9.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_9GRADE_PERCENT, grade9.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade10.text)) {
            val grade10 = editGrade10.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_10GRADE_PERCENT, grade10.toFloat())
        }
        if (!TextUtils.isEmpty(editGrade11.text)) {
            val grade11 = editGrade11.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_11GRADE_PERCENT, grade11.toFloat())
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