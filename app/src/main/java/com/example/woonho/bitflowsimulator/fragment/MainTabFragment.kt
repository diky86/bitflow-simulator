package com.example.woonho.osnbit.fragment

import android.annotation.TargetApi
import android.app.Fragment
import android.content.Context.INPUT_METHOD_SERVICE
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
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

    private lateinit var rootContainer: View

    private lateinit var editUserCommission: EditText
    private lateinit var editTraderCommission: EditText
    private lateinit var editCommission: EditText
    private lateinit var editBFTPrice: EditText

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

        val imm = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        // Root View
        rootContainer = v.findViewById(R.id.root_container)

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

        // 유저 배당
        editUserCommission = v.findViewById(R.id.edit_user_commission)
        editUserCommission.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_DIVIDEND).toString())
        editUserCommission.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val userCommission = editUserCommission.text.toString()
                if (!TextUtils.isEmpty(userCommission)) {
                    value = userCommission.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_DIVIDEND)
                }
                editUserCommission.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_DIVIDEND, value)
            } else {
                imm.showSoftInput(editUserCommission, 0)
                editUserCommission.setText("")
            }
        }
        editUserCommission.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editUserCommission.clearFocus()
                imm.hideSoftInputFromWindow(editUserCommission.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        editTraderCommission = v.findViewById(R.id.edit_trader_commission)
        editTraderCommission.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND).toString())
        editTraderCommission.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val traderCommission = editTraderCommission.text.toString()
                if (!TextUtils.isEmpty(traderCommission)) {
                    value = traderCommission.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND)
                }
                editTraderCommission.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND, value)
            } else {
                imm.showSoftInput(editTraderCommission, 0)
                editTraderCommission.setText("")
            }
        }
        editTraderCommission.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editTraderCommission.clearFocus()
                imm.hideSoftInputFromWindow(editTraderCommission.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        editCommission = v.findViewById(R.id.edit_commission)
        editCommission.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.TRADE_COMMISSION).toString())
        editCommission.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val commission = editCommission.text.toString()
                if (!TextUtils.isEmpty(commission)) {
                    value = commission.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.TRADE_COMMISSION)
                }
                editCommission.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.TRADE_COMMISSION, value)
            } else {
                imm.showSoftInput(editCommission, 0)
                editCommission.setText("")
            }
        }
        editCommission.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editCommission.clearFocus()
                imm.hideSoftInputFromWindow(editCommission.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        editBFTPrice = v.findViewById(R.id.edit_bft_price)
        editBFTPrice.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.BFT_PRICE).toString())
        editBFTPrice.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val bftPrice = editBFTPrice.text.toString()
                if (!TextUtils.isEmpty(bftPrice)) {
                    value = bftPrice.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.BFT_PRICE)
                }
                editBFTPrice.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.BFT_PRICE, value)
            } else {
                imm.showSoftInput(editBFTPrice, 0)
                editBFTPrice.setText("")
            }
        }
        editBFTPrice.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editBFTPrice.clearFocus()
                imm.hideSoftInputFromWindow(editBFTPrice.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        editGrade1 = v.findViewById(R.id.edit_grade_1)
        editGrade1.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_1GRADE_PERCENT).toString())
        editGrade1.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade1 = editGrade1.text.toString()
                if (!TextUtils.isEmpty(grade1)) {
                    value = grade1.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_1GRADE_PERCENT)
                }
                editGrade1.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_1GRADE_PERCENT, value)
            } else {
                editGrade1.setText("")
            }
        }

        editGrade2 = v.findViewById(R.id.edit_grade_2)
        editGrade2.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_2GRADE_PERCENT).toString())
        editGrade2.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade2 = editGrade2.text.toString()
                if (!TextUtils.isEmpty(grade2)) {
                    value = grade2.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_2GRADE_PERCENT)
                }
                editGrade2.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_2GRADE_PERCENT, value)
            } else {
                editGrade2.setText("")
            }
        }

        editGrade3 = v.findViewById(R.id.edit_grade_3)
        editGrade3.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_3GRADE_PERCENT).toString())
        editGrade3.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade3 = editGrade3.text.toString()
                if (!TextUtils.isEmpty(grade3)) {
                    value = grade3.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_3GRADE_PERCENT)
                }
                editGrade3.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_3GRADE_PERCENT, value)
            } else {
                editGrade3.setText("")
            }
        }

        editGrade4 = v.findViewById(R.id.edit_grade_4)
        editGrade4.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_4GRADE_PERCENT).toString())
        editGrade4.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade4 = editGrade4.text.toString()
                if (!TextUtils.isEmpty(grade4)) {
                    value = grade4.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_4GRADE_PERCENT)
                }
                editGrade4.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_4GRADE_PERCENT, value)
            } else {
                editGrade4.setText("")
            }
        }

        editGrade5 = v.findViewById(R.id.edit_grade_5)
        editGrade5.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_5GRADE_PERCENT).toString())
        editGrade5.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade5 = editGrade5.text.toString()
                if (!TextUtils.isEmpty(grade5)) {
                    value = grade5.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_5GRADE_PERCENT)
                }
                editGrade5.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_5GRADE_PERCENT, value)
            } else {
                editGrade5.setText("")
            }
        }

        editGrade6 = v.findViewById(R.id.edit_grade_6)
        editGrade6.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_6GRADE_PERCENT).toString())
        editGrade6.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade6 = editGrade6.text.toString()
                if (!TextUtils.isEmpty(grade6)) {
                    value = grade6.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_6GRADE_PERCENT)
                }
                editGrade6.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_6GRADE_PERCENT, value)
            } else {
                editGrade6.setText("")
            }
        }

        editGrade7 = v.findViewById(R.id.edit_grade_7)
        editGrade7.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_7GRADE_PERCENT).toString())
        editGrade7.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade7 = editGrade7.text.toString()
                if (!TextUtils.isEmpty(grade7)) {
                    value = grade7.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_7GRADE_PERCENT)
                }
                editGrade7.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_7GRADE_PERCENT, value)
            } else {
                editGrade7.setText("")
            }
        }

        editGrade8 = v.findViewById(R.id.edit_grade_8)
        editGrade8.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_8GRADE_PERCENT).toString())
        editGrade8.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade8 = editGrade8.text.toString()
                if (!TextUtils.isEmpty(grade8)) {
                    value = grade8.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_8GRADE_PERCENT)
                }
                editGrade8.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_8GRADE_PERCENT, value)
            } else {
                editGrade8.setText("")
            }
        }

        editGrade9 = v.findViewById(R.id.edit_grade_9)
        editGrade9.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_9GRADE_PERCENT).toString())
        editGrade9.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade9 = editGrade9.text.toString()
                if (!TextUtils.isEmpty(grade9)) {
                    value = grade9.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_9GRADE_PERCENT)
                }
                editGrade9.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_9GRADE_PERCENT, value)
            } else {
                editGrade9.setText("")
            }
        }

        editGrade10 = v.findViewById(R.id.edit_grade_10)
        editGrade10.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_10GRADE_PERCENT).toString())
        editGrade10.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade10 = editGrade10.text.toString()
                if (!TextUtils.isEmpty(grade10)) {
                    value = grade10.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_10GRADE_PERCENT)
                }
                editGrade10.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_10GRADE_PERCENT, value)
            } else {
                editGrade10.setText("")
            }
        }

        editGrade11 = v.findViewById(R.id.edit_grade_11)
        editGrade11.setText(
                PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_11GRADE_PERCENT).toString())
        editGrade11.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                val value: Float
                val grade11 = editGrade11.text.toString()
                if (!TextUtils.isEmpty(grade11)) {
                    value = grade11.toFloat()
                } else {
                    value = PreferenceHelper.getInstance(context).getFloatExtra(PreferenceConstants.USER_11GRADE_PERCENT)
                }
                editGrade11.setText(value.toString())
                PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.USER_11GRADE_PERCENT, value)
            } else {
                editGrade11.setText("")
            }
        }
        editGrade11.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE || event.keyCode == KeyEvent.KEYCODE_ENTER) {
                editGrade11.clearFocus()
                imm.hideSoftInputFromWindow(editGrade11.windowToken, 0)
                return@setOnEditorActionListener true
            }
            false
        }

        btnSetting = v.findViewById(R.id.btn_setting)
        btnSetting.setOnClickListener {
            saveSettingValue()
            Toast.makeText(context, "설정되었습니다.", Toast.LENGTH_LONG).show()
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
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

        // BFT 가격
        if (!TextUtils.isEmpty(editBFTPrice.text)) {
            val bftPrice = editBFTPrice.text.toString()
            PreferenceHelper.getInstance(context).putFloatExtra(PreferenceConstants.BFT_PRICE, bftPrice.toFloat())
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