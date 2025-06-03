package baohuy.projectx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountCalculatorTest {

    @Test
    public void testNoDiscount() {
        DiscountCalculator discountObj = new DiscountCalculator();
        //<100 => 0
        double total = discountObj.calculateDiscount(50);
        assertEquals(0, total);
    }

    @Test
    public void test10Discount() {
        DiscountCalculator discountObj = new DiscountCalculator();
        //<100 => 0
        double total = discountObj.calculateDiscount(150);
        assertEquals(15, total);
    }

    @Test
    public void test20Discount() {
        DiscountCalculator discountObj = new DiscountCalculator();
        //<100 => 0
        double total = discountObj.calculateDiscount(600);
        assertEquals(120, total);
    }

}
