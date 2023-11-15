package christmas.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChristmasDiscountPolicyTest {
    private ChristmasDiscountPolicy christmasPolicy;


    @DisplayName("이벤트 적용 여부가 제대로 검증되는지 확인한다.")
    @Test
    public void testIsEventApplicable() {
        christmasPolicy = new ChristmasDiscountPolicy(10);
        for (int date = 1; date <= 25; date++) {
            assertTrue(christmasPolicy.isEventApplicable(date));
        }

        assertFalse(christmasPolicy.isEventApplicable(30));
        assertFalse(christmasPolicy.isEventApplicable(26));
    }

    @DisplayName("날짜에 맞게 할인 금액이 잘 계산되는지 확인한다.")
    @Test
    public void testCalculateDiscountAmount() {
        ChristmasDiscountPolicy policy1 = new ChristmasDiscountPolicy(1);
        assertEquals(1000, policy1.calculateDiscountAmount(10000));

        ChristmasDiscountPolicy policy2 = new ChristmasDiscountPolicy(10);
        assertEquals(1900, policy2.calculateDiscountAmount(10000));

        ChristmasDiscountPolicy policy3 = new ChristmasDiscountPolicy(25);
        assertEquals(3400, policy3.calculateDiscountAmount(10000));

        ChristmasDiscountPolicy policy4 = new ChristmasDiscountPolicy(30);
        assertEquals(0, policy4.calculateDiscountAmount(10000));
    }

    @DisplayName("초기 할인 금액 검증한다.")
    @Test
    public void testGetDiscountAmount() {
        christmasPolicy = new ChristmasDiscountPolicy(10);
        assertEquals(1000, christmasPolicy.getDiscountAmount());
    }

    @DisplayName("유효하지 않은 날짜가 입력되면 IllegalArgumentException 발생")
    @Test
    public void testInvalidDate() {
        assertThrows(IllegalArgumentException.class, () -> new ChristmasDiscountPolicy(0));
        assertThrows(IllegalArgumentException.class, () -> new ChristmasDiscountPolicy(32));
    }
}