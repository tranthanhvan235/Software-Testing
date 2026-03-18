package com.example;

public class ShopeePromotionService {
    public int getFinalVoucher(double spending, int orders, int hour, String paymentMethod) {
        // check input validity
        if (spending < 0 || spending > 500 || orders < 0 || orders > 100 || hour < 0 || hour > 23) {
            return -1;
        }
        if (paymentMethod == null) return -1;

        int discount = 0;
        boolean isFlashSale = (hour == 0 || hour == 12);
        boolean isShopeePay = paymentMethod.equals("ShopeePay");

        // ranking and voucher calculation
        if (spending >= 15.0 && orders >= 50) { // diamond
            if (isFlashSale) {
                discount = isShopeePay ? 50 : 35;
            } else {
                discount = isShopeePay ? 30 : 25;
            }
        } 
        else if (spending >= 5.0 && orders >= 15) { // gold 
            if (isFlashSale) {
                discount = isShopeePay ? 30 : 20;
            } else {
                discount = 15;
            }
        } 
        else if (spending >= 1.0 && orders >= 3) { // silver
            if (hour == 12 && isShopeePay) {
                discount = 20;
            } else {
                discount = 10;
            }
        } 
        else { // F0
            discount = isShopeePay ? 5 : 0;
        }
        return discount;
    }

    // public static void main(String[] args) {
    //     ShopeePromotionService service = new ShopeePromotionService();

    //     // Run some test cases
    //     System.out.println("Testing ShopeePromotionService...");
        
    //     //Test 1: diamond, flash sale, ShopeePay
    //     int res1 = service.getFinalVoucher(20.0, 60, 0, "ShopeePay");
    //     System.out.println("Case 1 (Diamond, 0h, SP): " + res1 + "%"); // Expected: 50

    //     // Test 2: gold, regular hour, COD
    //     int res2 = service.getFinalVoucher(10.0, 30, 15, "COD");
    //     System.out.println("Case 2 (Gold, 15h, COD): " + res2 + "%"); // Expected: 15

    //     // Test 3: F0, ShopeePay
    //     int res3 = service.getFinalVoucher(0.5, 1, 12, "ShopeePay");
    //     System.out.println("Case 3 (F0, 12h, SP): " + res3 + "%"); // Expected: 5
    // }
}