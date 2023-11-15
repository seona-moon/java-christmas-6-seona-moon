package christmas.model.order;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.view.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderMenuTest {

    @Test
    @DisplayName("유효한 메뉴와 개수를 입력할 경우 주문이 성공해야 한다")
    void orderWithValidMenuAndCount() {
        String input = "해산물파스타-2,레드와인-1,초코케이크-1";
        OrderMenu order = new OrderMenu(input);
        int totalItemCount = order.getMenus().values().stream().mapToInt(Integer::intValue).sum();

        assertThat(order.getMenus().size()).isEqualTo(3);
        assertThat(totalItemCount).isEqualTo(4);
        System.out.println(order);
    }

    @Test
    @DisplayName("메뉴판에 없는 메뉴를 입력할 경우 에러가 발생해야 한다")
    void orderWithInvalidMenu() {
        String input = "없는메뉴-1";
        assertThatThrownBy(() -> new OrderMenu(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.INVALID_MENU_ERROR.getMessage());
    }

    @Test
    @DisplayName("메뉴 개수로 0이나 음수를 입력할 경우 에러가 발생해야 한다")
    void orderWithInvalidCount() {
        String input = "해산물파스타-0";
        assertThatThrownBy(() -> new OrderMenu(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.INVALID_MENU_ERROR.getMessage());
    }

    @Test
    @DisplayName("잘못된 형식의 주문을 입력할 경우 에러가 발생해야 한다")
    void orderWithInvalidFormat() {
        String input = "해산물파스타/2";
        assertThatThrownBy(() -> new OrderMenu(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.INVALID_MENU_ERROR.getMessage());
    }

    @Test
    @DisplayName("중복된 메뉴를 입력할 경우 에러가 발생해야 한다")
    void orderWithDuplicateMenu() {
        String input = "시저샐러드-1,시저샐러드-1";
        assertThatThrownBy(() -> new OrderMenu(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.DUPLICATE_MENU_ERROR.getMessage());
    }

    @Test
    @DisplayName("음료만 주문할 경우 에러가 발생해야 한다")
    void orderBeveragesOnly() {
        String input = "제로콜라-2,레드와인-1";
        assertThatThrownBy(() -> new OrderMenu(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.BEVERAGE_ONLY_ERROR.getMessage());
    }

    @Test
    @DisplayName("한 번에 주문할 수 있는 최대 개수를 초과할 경우 에러가 발생해야 한다")
    void orderWithExceedingMaxItems() {
        String input = "해산물파스타-10,레드와인-10,초코케이크-1"; // 총 개수 21개
        assertThatThrownBy(() -> new OrderMenu(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(ErrorMessage.MAX_ITEM_COUNT_EXCEEDED_ERROR.getMessage());
    }
}
