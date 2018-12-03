package com.example.woonho.bitflowsimulator;

import android.content.Context;

public class CalculatorUtils {

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

    public float computeUserRebaseBFT() {
        return 0;
    }

    public float computeUserRebaseBTC() {
        return 0;
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
}
