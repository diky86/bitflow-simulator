package com.example.woonho.bitflowsimulator;

import android.content.Context;

public class CalculatorUtils {

    private static final String TAG = "CalculatorUtils";
    private Context mContext;

    public CalculatorUtils(Context context) {
        mContext = context;
    }

    /**
     * 거래 금액 * 수수료
     * @param miningTurnTransaction : 마이닝턴 거래 금액
     * @return 마이닝턴 발생 수수료
     */
    public float computeMiningTurnCommission(float miningTurnTransaction) {
        return (float) (miningTurnTransaction * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.TRADE_COMMISSION) * 0.01));
    }

    /**
     * 발생 수수료 * 거래소 배당 - BTC (전송 수수료 포함)
     * @param miningTurnCommission : 마이닝턴 발생 수수료
     * @return 마이닝턴 거래소 배당
     */
    public float computeMiningTurnExchangeDividend(float miningTurnCommission) {
        return (float) (miningTurnCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND) * 0.01)
                        - ((computeGradeBtc(miningTurnCommission))));
    }

    /**
     * 발생 수수료 * 유저 배당
     * @param miningTurnCommission : 마이닝턴 발생 수수료
     * @return 마이닝턴 유저 배당
     */
    public float computeMiningTurnUserDividend(float miningTurnCommission) {
        return (float) (miningTurnCommission * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_DIVIDEND) * 0.01));
    }

    /**
     * 거래 금액 * 수수료
     * @param refillTurnTransaction : 리필턴 거래 금액
     * @return 리필턴 발생 수수료
     */
    public float computeRefillTurnCommission(float refillTurnTransaction) {
        return (float) (refillTurnTransaction * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.TRADE_COMMISSION) * 0.01));
    }

    /**
     * 발생 수수료 * 0.2 (BFT 구매 후 남은 비율) * 거래소 배당
     * @param refillTurnCommission : 리필턴 발생 수수료
     * @return 리필턴 거래소 배당
     */
    public float computeRefillTurnExchangeDividend(float refillTurnCommission) {
        return (float) ((refillTurnCommission * 0.2) * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND) * 0.01));
    }

    /**
     * 발생 수수료 * 0.2 (BFT 구매 후 남은 비율) * 유저 배당
     * @param refillTurnCommission 리필턴 발생 수수료
     * @return 리필턴 유저 배당
     */
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

        float miningTurnRevenue = (tradeCommission
                * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND) / 100))
                - (computeGradeBtc(tradeCommission));

        float refillTurnRevenue =
                (float) (tradeCommission
                * 0.2
                * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.EXCHANGE_DIVIDEND) / 100));
        return miningTurnRevenue + refillTurnRevenue;
    }

    public float computeTurnStandardUserRevenue(float tradeCommission) {

        float miningTurnRevenue = (tradeCommission
                * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_DIVIDEND) / 100));

        float refillTurnRevenue =
                (float) (tradeCommission
                        * 0.2
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_DIVIDEND) / 100));

        return miningTurnRevenue + refillTurnRevenue;
    }

    private float computeGradeBtc(float tradeCommission) {
        float grade1Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_1GRADE_PERCENT) / 100)
                        * 0.01
                        * (1.11));

        float grade2Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_2GRADE_PERCENT) / 100)
                        * 0.02
                        * (1.11));

        float grade3Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_3GRADE_PERCENT) / 100)
                        * 0.03
                        * (1.11));

        float grade4Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_4GRADE_PERCENT) / 100)
                        * 0.04
                        * (1.11));

        float grade5Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_5GRADE_PERCENT) / 100)
                        * 0.05
                        * (1.11));

        float grade6Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_6GRADE_PERCENT) / 100)
                        * 0.06
                        * (1.11));

        float grade7Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_7GRADE_PERCENT) / 100)
                        * 0.07
                        * (1.11));

        float grade8Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_8GRADE_PERCENT) / 100)
                        * 0.08
                        * (1.11));

        float grade9Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_9GRADE_PERCENT) / 100)
                        * 0.09
                        * (1.11));

        float grade10Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_10GRADE_PERCENT) / 100)
                        * 0.1
                        * (1.11));

        float grade11Btc = (float) (tradeCommission
                        * (PreferenceHelper.getInstance(mContext).getFloatExtra(PreferenceConstants.USER_11GRADE_PERCENT) / 100)
                        * 0.11
                        * (1.11));

        return grade1Btc + grade2Btc + grade3Btc + grade4Btc
                + grade5Btc + grade6Btc + grade7Btc + grade8Btc
                + grade9Btc + grade10Btc + grade11Btc;
    }
}
