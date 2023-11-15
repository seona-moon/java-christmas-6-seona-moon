package christmas.model.event;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import static org.assertj.core.api.Assertions.assertThat;

class WeekendDiscountEventPolicyTest {

    @Test
    @DisplayName("주말에는 할인이 적용됨")
    void discountAppliedOnWeekends() {
        WeekendDiscountEventPolicy policy = new WeekendDiscountEventPolicy();
        assertThat(policy.isEventApplicable(1)).isTrue(); // 금요일 가정
        assertThat(policy.isEventApplicable(2)).isTrue(); // 토요일 가정
    }

    @Test
    @DisplayName("평일에는 할인이 적용되지 않음")
    void noDiscountOnWeekdays() {
        WeekendDiscountEventPolicy policy = new WeekendDiscountEventPolicy();
        assertThat(policy.isEventApplicable(3)).isFalse(); // 일요일 가정
    }

    @Test
    @DisplayName("주말에 메인 메뉴 할인이 정확히 계산됨")
    void correctDiscountCalculationOnWeekends() {
        WeekendDiscountEventPolicy policy = new WeekendDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.TBoneSteak, 1); // 메인 메뉴를 하나 추가한다고 가정
        int discount = policy.getDiscountAmount(1, menus); // 금요일 가정
        assertThat(discount).isEqualTo(2023);
    }

    @Test
    @DisplayName("주말이 아닐 때 메인 메뉴 할인이 계산되지 않음")
    void noDiscountCalculationOnWeekdays() {
        WeekendDiscountEventPolicy policy = new WeekendDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.TBoneSteak, 1); // 메인 메뉴를 하나 추가한다고 가정
        int discount = policy.getDiscountAmount(3, menus); // 일요일 가정
        assertThat(discount).isEqualTo(0);
    }
}
