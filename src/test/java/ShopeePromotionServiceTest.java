import org.junit.jupiter.api.Test;

import com.example.ShopeePromotionService;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShopeePromotionServiceTest {

    @Test 
    void testGetFinalVoucher() {
        ShopeePromotionService service = new ShopeePromotionService();
        
        assertAll(
            () -> assertEquals(15, service.getFinalVoucher(10.0, 30, 15, "ShopeePay")), // TC1 (Norm)
            () -> assertEquals(5, service.getFinalVoucher(0, 30, 15, "ShopeePay")),    // TC2
            () -> assertEquals(5, service.getFinalVoucher(0.9, 30, 15, "ShopeePay")),  // TC3
            () -> assertEquals(10, service.getFinalVoucher(1.0, 30, 15, "ShopeePay")),  // TC4
            () -> assertEquals(10, service.getFinalVoucher(4.9, 30, 15, "ShopeePay")),  // TC5
            () -> assertEquals(15, service.getFinalVoucher(5.0, 30, 15, "ShopeePay")),  // TC6
            () -> assertEquals(15, service.getFinalVoucher(14.9, 30, 15, "ShopeePay")), // TC7
            () -> assertEquals(15, service.getFinalVoucher(15.0, 30, 15, "ShopeePay")), // TC8
            () -> assertEquals(5, service.getFinalVoucher(10.0, 0, 15, "ShopeePay")),   // TC9
            () -> assertEquals(5, service.getFinalVoucher(10.0, 2, 15, "ShopeePay")),   // TC10
            () -> assertEquals(10, service.getFinalVoucher(10.0, 3, 15, "ShopeePay")),  // TC11
            () -> assertEquals(10, service.getFinalVoucher(10.0, 14, 15, "ShopeePay")), // TC12
            () -> assertEquals(15, service.getFinalVoucher(10.0, 15, 15, "ShopeePay")), // TC13
            () -> assertEquals(15, service.getFinalVoucher(10.0, 49, 15, "ShopeePay")), // TC14
            () -> assertEquals(15, service.getFinalVoucher(10.0, 50, 15, "ShopeePay"))  // TC15
        );
    }
}