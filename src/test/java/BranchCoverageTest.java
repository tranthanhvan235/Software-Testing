import com.example.ShopeePromotionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BranchCoverageTest {

	private final ShopeePromotionService service = new ShopeePromotionService();

	static Stream<Arguments> getFinalVoucherBranchCases() {
		return Stream.of(
			Arguments.of(-1.0, 50, 0, "ShopeePay", -1),
			Arguments.of(15.0, 50, 0, null, -1),
			Arguments.of(15.0, 50, 0, "ShopeePay", 50),
			Arguments.of(15.0, 50, 0, "COD", 35),
			Arguments.of(15.0, 50, 1, "ShopeePay", 30),
			Arguments.of(15.0, 50, 1, "COD", 25),
			Arguments.of(5.0, 15, 0, "ShopeePay", 30),
			Arguments.of(5.0, 15, 0, "COD", 20),
			Arguments.of(5.0, 15, 1, "ShopeePay", 15),
			Arguments.of(1.0, 3, 12, "ShopeePay", 20),
			Arguments.of(1.0, 3, 2, "ShopeePay", 10),
			Arguments.of(1.0, 2, 0, "ShopeePay", 5),
			Arguments.of(1.0, 2, 0, "COD", 0)
		);
	}

	@ParameterizedTest(name = "spending={0}, orders={1}, hour={2}, payment={3} => expected={4}")
	@MethodSource("getFinalVoucherBranchCases")
	@DisplayName("Branch coverage cases for getFinalVoucher")
	void testGetFinalVoucherBranchCoverage(double spending, int orders, int hour, String payment, int expected) {
		int actual = service.getFinalVoucher(spending, orders, hour, payment);
		Assertions.assertEquals(expected, actual);
	}
}
