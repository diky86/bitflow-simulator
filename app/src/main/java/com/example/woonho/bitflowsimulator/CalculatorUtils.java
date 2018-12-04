package com.example.woonho.bitflowsimulator;

import android.content.Context;

public class CalculatorUtils {

    private static final String TAG = "CalculatorUtils";
    private Context mContext;

    public CalculatorUtils(Context context) {
        mContext = context;
    }

    public float computeMiningTurnCommission(float miningTurnTransaction) {
        return (float) (miningTurnTransaction * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.TRADE_COMMISSION) * 0.01));
    }

    public float computeMiningTurnExchangeDividend(float miningTurnCommission) {
        return (float) (miningTurnCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND) * 0.01)
                        - ((miningTurnCommission * 0.01) * 0.89));
    }

    public float computeMiningTurnUserDividend(float miningTurnCommission) {
        return (float) (miningTurnCommission * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_DIVIDEND) * 0.01));
    }

    public float computeRefillTurnCommission(float refillTurnTransaction) {
        return (float) (refillTurnTransaction * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.TRADE_COMMISSION) * 0.01));
    }

    public float computeRefillTurnExchangeDividend(float refillTurnCommission) {
        return (float) ((refillTurnCommission * 0.2) * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND) * 0.01));
    }

    public float computeRefillTurnUserDividend(float refillTurnCommission) {
        return (float) ((refillTurnCommission * 0.2) * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_DIVIDEND) * 0.01));
    }

    public float computeRefillTurnBFTBuying(float refillTurnCommission) {
        return (float) (refillTurnCommission * 0.8);
    }

    /**
     * BFT 수량 * BFT 가격 * 1/수수료
     * @param bftPrice : BFT 가격
     * @param bftCount : BFT 수량
     * @return 거래한 금액
     */
    public float computeTurnStandardTradePrice(float bftPrice, float bftCount) {
        return bftPrice * bftCount * (1 / PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.TRADE_COMMISSION));
    }

    /**
     * 거래한 금액 * 수수료
     * @param tradedPrice : 거래한 금액
     * @return 발생 수수료
     */
    public float computeTurnStandardTradedCommision(float tradedPrice) {
        return tradedPrice * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.TRADE_COMMISSION) / 100);
    }

    public float computeTurnStandardExchangeRevenue(float tradeCommission) {
        return (tradeCommission * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND) / 100))
                - (computeGradeBtc(tradeCommission));
    }

    private float computeGradeBtc(float tradeCommission) {
        float grade1Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_1GRADE_PERCENT) / 100)
                        * 0.01
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade2Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_2GRADE_PERCENT) / 100)
                        * 0.02
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade3Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_3GRADE_PERCENT) / 100)
                        * 0.03
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade4Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_4GRADE_PERCENT) / 100)
                        * 0.04
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade5Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_5GRADE_PERCENT) / 100)
                        * 0.05
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade6Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_6GRADE_PERCENT) / 100)
                        * 0.06
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade7Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_7GRADE_PERCENT) / 100)
                        * 0.07
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade8Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_8GRADE_PERCENT) / 100)
                        * 0.08
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade9Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_9GRADE_PERCENT) / 100)
                        * 0.09
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade10Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_10GRADE_PERCENT) / 100)
                        * 0.1
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        float grade11Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_11GRADE_PERCENT) / 100)
                        * 0.11
                        * (1 + PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.BTC_SEND_COMMISSION)));

        return grade1Btc + grade2Btc + grade3Btc + grade4Btc
                + grade5Btc + grade6Btc + grade7Btc + grade8Btc
                + grade9Btc + grade10Btc + grade11Btc;
    }
}
