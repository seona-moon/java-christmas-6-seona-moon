package christmas.model.event;

import christmas.model.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.EnumMap;
import static org.assertj.core.api.Assertions.assertThat;

class GiftEventPolicyTest {

    @Test
    @DisplayName("총액이 증정 기준 금액 이상일 때 적용")
    void shouldBeApplicableWhenTotalAmountExceeds() {
        GiftEventPolicy policy = new GiftEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.TBoneSteak, 2);
        menus.put(Menu.ChocolateCake, 1);

        assertThat(policy.isEventApplicable(menus)).isTrue();
    }

    @Test
    @DisplayName("총액이 증정 기준 금액 미만일 때 미적용")
    void shouldNotBeApplicableWhenTotalAmountBelow() {
        GiftEventPolicy policy = new GiftEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.TBoneSteak, 1);

        assertThat(policy.isEventApplicable(menus)).isFalse();
    }

    @Test
    @DisplayName("이벤트 적용 시 샴페인 가격만큼 할인 반환")
    void shouldReturnChampagnePriceAsDiscountWhenApplicable() {
        GiftEventPolicy policy = new GiftEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.ChristmasPasta, 5);

        assertThat(policy.getDiscountAmount(0, menus)).isEqualTo(25_000);
    }

    @Test
    @DisplayName("이벤트 미적용 시 할인 0원 반환")
    void shouldReturnZeroAsDiscountWhenNotApplicable() {
        GiftEventPolicy policy = new GiftEventPolicy();
        EnumMap<Menu, Integer> menus = new EnumMap<>(Menu.class);
        menus.put(Menu.ChocolateCake, 1);

        assertThat(policy.getDiscountAmount(0, menus)).isEqualTo(0);
    }
}
