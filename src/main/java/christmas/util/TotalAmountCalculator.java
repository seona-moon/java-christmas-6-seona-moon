package christmas.util;

import christmas.model.menu.Menu;
import java.util.EnumMap;
import java.util.Map;

public class TotalAmountCalculator {
    public static int calculateTotalAmount(EnumMap<Menu, Integer> menus) {
        return menus.entrySet().stream()
            .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
            .sum();
    }


    public static int calculateTotalDiscount(Map<String, Integer> benefits) {
        return benefits.values().stream().mapToInt(Integer::intValue).sum();
    }
}
