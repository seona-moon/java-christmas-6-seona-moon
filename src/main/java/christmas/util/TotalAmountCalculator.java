package christmas.util;

import christmas.model.menu.Menu;
import java.util.EnumMap;

public class TotalAmountCalculator {
    public static int calculateTotalAmount(EnumMap<Menu, Integer> menus) {
        return menus.entrySet().stream()
            .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }
}
