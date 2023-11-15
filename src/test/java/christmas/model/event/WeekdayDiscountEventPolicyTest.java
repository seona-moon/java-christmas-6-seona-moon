package christmas.model.event;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import static org.assertj.core.api.Assertions.assertThat;

class WeekdayDiscountEventPolicyTest {

    @Test
    @DisplayName("평일에 할인 적용")
    void discountAppliedOnWeekdays() {
        WeekdayDiscountEventPolicy policy = new WeekdayDiscountEventPolicy();
        assertThat(policy.isEventApplicable(4)).isTrue();
    }

    @Test
    @DisplayName("주말에 할인 미적용")
    void noDiscountOnWeekends() {
        WeekdayDiscountEventPolicy policy = new WeekdayDiscountEventPolicy();
        assertThat(policy.isEventApplicable(1)).isFalse();
    }

    @Test
    @DisplayName("적용 날짜에 정확한 할인 계산")
    void correctDiscountCalculationOnApplicableDate() {
        WeekdayDiscountEventPolicy policy = new WeekdayDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.ChocolateCake, 1);
        menus.put(Menu.IceCream, 2);
        int discount = policy.getDiscountAmount(4, menus);
        assertThat(discount).isEqualTo(2 * 2023 + 2023);
    }

    @Test
    @DisplayName("비적용 날짜에 할인 없음")
    void noDiscountOnNonApplicableDate() {
        WeekdayDiscountEventPolicy policy = new WeekdayDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.ChocolateCake, 1);
        int discount = policy.getDiscountAmount(1, menus);
        assertThat(discount).isEqualTo(0);
    }
}
