package christmas.model.event;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountEventPolicyTest {

    @Test
    @DisplayName("이벤트 기간 내의 날짜에 대해 isEventApplicable 메서드가 true를 반환하는지 확인")
    void isEventApplicable_WithinEventPeriod_ReturnsTrue() {
        ChristmasDiscountEventPolicy policy = new ChristmasDiscountEventPolicy();
        assertThat(policy.isEventApplicable(10)).isTrue();
    }

    @Test
    @DisplayName("이벤트 기간 외의 날짜에 대해 isEventApplicable 메서드가 false를 반환하는지 확인")
    void isEventApplicable_OutsideEventPeriod_ReturnsFalse() {
        ChristmasDiscountEventPolicy policy = new ChristmasDiscountEventPolicy();
        assertThat(policy.isEventApplicable(30)).isFalse();
    }

    @Test
    @DisplayName("이벤트 기간 내의 날짜에 대해 getDiscountAmount 메서드가 올바른 할인 금액을 반환하는지 확인")
    void getDiscountAmount_WithinEventPeriod_ReturnsCorrectDiscount() {
        ChristmasDiscountEventPolicy policy = new ChristmasDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class); // Assume this is populated accordingly
        int discount = policy.getDiscountAmount(10, menus);
        assertThat(discount).isEqualTo(1900); // Assuming 10 is the date, the discount should be 1000 + (10-1)*100
    }

    @Test
    @DisplayName("이벤트 기간 외의 날짜에 대해 getDiscountAmount 메서드가 0을 반환하는지 확인")
    void getDiscountAmount_OutsideEventPeriod_ReturnsZero() {
        ChristmasDiscountEventPolicy policy = new ChristmasDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class); // Assume this is populated accordingly
        int discount = policy.getDiscountAmount(30, menus);
        assertThat(discount).isEqualTo(0);
    }

    @Test
    @DisplayName("날짜가 크리스마스에 가까워짐에 따라 getDiscountAmount 메서드가 할인 금액을 올바르게 증가시키는지 확인")
    void getDiscountAmount_ScalesCorrectlyAsDatesApproachChristmas() {
        ChristmasDiscountEventPolicy policy = new ChristmasDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class); // Assume this is populated accordingly
        int discountEarly = policy.getDiscountAmount(1, menus);
        int discountLater = policy.getDiscountAmount(25, menus);
        assertThat(discountEarly).isEqualTo(1000);
        assertThat(discountLater).isEqualTo(3400); // On the 25th, the discount should be 1000 + (25-1)*100
    }
}
