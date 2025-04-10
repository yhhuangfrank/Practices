package com.frank.mytest.codetest.solid.srp;

public class SingleResponsibilityPrinciple {

    interface FeeCalculator {
        int cal(double percentage);
    }

    // 專門計算 Credit card Fee
    class CreditCardFeeHandler implements FeeCalculator {

        @Override
        public int cal(double percentage) {
            return 0;
        }
    }

    // 專門計算 Tax
    class TaxFeeHandler implements FeeCalculator {

        @Override
        public int cal(double percentage) {
            return 0;
        }
    }
}
