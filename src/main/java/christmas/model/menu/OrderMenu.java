package christmas.model.menu;

import christmas.view.ErrorMessage;
import java.util.EnumMap;
import java.util.Optional;

public class OrderMenu {
    private static final int MAX_ITEMS = 20;
    private EnumMap<Menu, Integer> menus;

    public OrderMenu(String input) {
        menus = new EnumMap<>(Menu.class);
        parseAndSetMenus(input);
        validateOrder();
    }

    public EnumMap<Menu, Integer> getMenus() {
        return menus;
    }

    private void parseAndSetMenus(String input) {
        String[] menuOrders = input.split(",");
        for (String menuOrder : menuOrders) {
            processMenuOrder(menuOrder);
        }
    }

    private void processMenuOrder(String menuOrder) {
        validateMenuOrderFormat(menuOrder);
        String[] parts = menuOrder.trim().split("-");
        addMenu(parts[0], Integer.parseInt(parts[1]));
    }

    private void validateMenuOrderFormat(String menuOrder) {
        if (!menuOrder.matches("([가-힣]+)-([1-9][0-9]*)")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_ERROR.getMessage());
        }
    }

    private void addMenu(String menuName, int menuCount) {
        Menu menu = getMenuByName(menuName).orElseThrow(this::invalidMenuException);
        menus.merge(menu, menuCount, (oldCount, newCount) -> duplicateMenuException());
    }

    private Optional<Menu> getMenuByName(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.getName().equalsIgnoreCase(menuName)) {
                return Optional.of(menu);
            }
        }
        return Optional.empty();
    }

    private IllegalArgumentException invalidMenuException() {
        return new IllegalArgumentException(ErrorMessage.INVALID_MENU_ERROR.getMessage());
    }

    private Integer duplicateMenuException() {
        throw new IllegalArgumentException(ErrorMessage.DUPLICATE_MENU_ERROR.getMessage());
    }

    private void validateOrder() {
        int totalItemCount = menus.values().stream().mapToInt(Integer::intValue).sum();

        if (totalItemCount > MAX_ITEMS) {
            throw new IllegalArgumentException(ErrorMessage.MAX_ITEM_COUNT_EXCEEDED_ERROR.getMessage());
        }
        if (isBeverageOnlyOrder()) {
            throw new IllegalArgumentException(ErrorMessage.BEVERAGE_ONLY_ERROR.getMessage());
        }
    }

    private boolean isBeverageOnlyOrder() {
        return menus.keySet().stream().allMatch(menu -> menu.getType() == MenuType.Beverage);
    }
}
