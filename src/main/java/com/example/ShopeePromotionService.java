package com.example;

public class ShopeePromotionService {
    public int getFinalVoucher(double spending, int orders, int hour, String paymentMethod) {
        // check input validity
        if (spending < 0 || spending > 500 || orders < 0 || orders > 100 || hour < 0 || hour > 23) {
            return -1;
        }
        if (paymentMethod == null)
            return -1;

        int discount = 0;
        boolean isFlashSale = (hour == 0 || hour == 12);
        boolean isShopeePay = paymentMethod.equals("ShopeePay");

        // ranking and voucher calculation
        if (spending >= 15.0 && orders >= 50) { // diamond
            if (isFlashSale) {
                if (isShopeePay)
                    return 50;
                else
                    return 35;
            } else {
                if (isShopeePay)
                    return 30;
                else
                    return 25;
            }
        }
        if (spending >= 5.0 && orders >= 15) { // gold
            if (isFlashSale) {
                if (isShopeePay)
                    return 30;
                else
                    return 20;
            } else {
                return 15;
            }
        }
        if (spending >= 1.0 && orders >= 3) { // silver
            if (hour == 12 && isShopeePay) {
                return 20;
            } else {
                return 10;
            }
        }
        // F0
        if (isShopeePay)
            return 5;
        else
            return 0;

    }
}