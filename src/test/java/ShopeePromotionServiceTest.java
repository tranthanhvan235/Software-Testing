// import org.junit.jupiter.api.Test;

import com.example.ShopeePromotionService;

// import static org.junit.jupiter.api.Assertions.assertAll;
// import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ShopeePromotionServiceTest {

    private final ShopeePromotionService service = new ShopeePromotionService();

    @ParameterizedTest(name = "{0}: spending={1}, orders={2}, hour={3}, payment={4} => expected={5}%")
    @DisplayName("Shopee Promotion Service - 23 Test Cases for getFinalVoucher()")
    @CsvSource({
        // STT, Spending, Orders, Hour, Payment, Expected
        
        "TC1,  15.0,  50,  0,  'ShopeePay', 50", // Diamond + 0h + SP
        "TC2,  20.0,  60,  12, 'ShopeePay', 50", // Diamond + 12h + SP
        "TC3,  16.0,  55,  14, 'ShopeePay', 30", // Diamond + common hour + SP
        "TC4,  15.0,  50,  9,  'COD',       25", // Diamond + common hour + COD

        "TC5,  5.0,   15,  0,  'ShopeePay', 30", // Gold + 0h + SP
        "TC6,  10.0,  30,  12, 'ShopeePay', 30", // Gold + 12h + SP
        "TC7,  5.0,   15,  15, 'ShopeePay', 15", // Gold + common hour (Code default 15)
        "TC8,  7.0,   25,  8,  'COD',       15", // Gold + common hour + COD
        
        "TC9,  1.0,   3,   12, 'ShopeePay', 20", // Silver + 12h + SP (for Silver)
        "TC10, 1.0,   3,   0,  'ShopeePay', 10", // Silver + 0h + SP (rank)
        "TC11, 2.0,   5,   10, 'ShopeePay', 10", // Silver + common hour + SP
        "TC12, 1.5,   4,   14, 'COD',       10", // Silver + COD
        
        "TC13, 0.5,   1,   0,  'ShopeePay', 5",  // F0 + 0h + SP
        "TC14, 0.2,   0,   12, 'ShopeePay', 5",  // F0 + 12h + SP
        "TC15, 0.1,   1,   15, 'COD',       0",  // F0 + COD (Code return 0)

        "TC16, -1.0,  10,  12, 'ShopeePay', -1", // Spending negative
        "TC17, 501.0, 50,  12, 'ShopeePay', -1", // Spending exceeds threshold
        "TC18, 10.0,  -5,  12, 'ShopeePay', -1", // Orders negative
        "TC19, 10.0,  101, 12, 'ShopeePay', -1", // Orders exceeds threshold
        "TC20, 10.0,  10,  24, 'ShopeePay', -1", // Hour invalid

        "TC21, 14.9,  50,  0,  'ShopeePay', 30", // Insufficient Diamond => Calculate by Gold tier
        "TC22, 5.0,   14,  12, 'ShopeePay', 20", // Insufficient Gold => Calculate by Silver tier
        "TC23, 1.0,   2,   12, 'ShopeePay', 5"   // Insufficient Silver => Calculate by F0 tier
    })
    void testGetFinalVoucher(String id, double spending, int orders, int hour, String payment, int expected) {
        int actual = service.getFinalVoucher(spending, orders, hour, payment);
        
        Assertions.assertEquals(expected, actual, 
            String.format("Thất bại tại %s: Mong đợi %d nhưng nhận được %d", id, expected, actual));
    }
}