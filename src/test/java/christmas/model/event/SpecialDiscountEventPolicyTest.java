package christmas.model.event;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountEventPolicyTest {

    @Test
    @DisplayName("특별 할인 날짜에 true 반환")
    void returnsTrueOnSpecialDiscountDays() {
        SpecialDiscountEventPolicy policy = new SpecialDiscountEventPolicy();

        assertThat(policy.isEventApplicable(3)).isTrue();
        assertThat(policy.isEventApplicable(10)).isTrue();
        assertThat(policy.isEventApplicable(17)).isTrue();
        assertThat(policy.isEventApplicable(24)).isTrue();
        assertThat(policy.isEventApplicable(25)).isTrue();
        assertThat(policy.isEventApplicable(31)).isTrue();
    }

    @Test
    @DisplayName("특별 할인 날짜가 아닐 때 false 반환")
    void returnsFalseOnNonSpecialDiscountDays() {
        SpecialDiscountEventPolicy policy = new SpecialDiscountEventPolicy();

        assertThat(policy.isEventApplicable(1)).isFalse();
        assertThat(policy.isEventApplicable(2)).isFalse();
        assertThat(policy.isEventApplicable(4)).isFalse();
        assertThat(policy.isEventApplicable(26)).isFalse();
    }

    @Test
    @DisplayName("특별 할인 날짜에 할인이 적용되어야 함")
    void discountAppliedOnSpecialDays() {
        SpecialDiscountEventPolicy policy = new SpecialDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);

        assertThat(policy.getDiscountAmount(3, menus)).isEqualTo(1000);
        assertThat(policy.getDiscountAmount(10, menus)).isEqualTo(1000);
        assertThat(policy.getDiscountAmount(17, menus)).isEqualTo(1000);
        assertThat(policy.getDiscountAmount(24, menus)).isEqualTo(1000);
        assertThat(policy.getDiscountAmount(25, menus)).isEqualTo(1000);
        assertThat(policy.getDiscountAmount(31, menus)).isEqualTo(1000); // 31은 특별 할인 날짜임
    }

    @Test
    @DisplayName("특별 할인 날짜가 아니면 할인이 적용되지 않아야 함")
    void noDiscountAppliedOnNonSpecialDays() {
        SpecialDiscountEventPolicy policy = new SpecialDiscountEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);

        assertThat(policy.getDiscountAmount(1, menus)).isEqualTo(0);
        assertThat(policy.getDiscountAmount(2, menus)).isEqualTo(0);
        assertThat(policy.getDiscountAmount(26, menus)).isEqualTo(0);
    }
}
