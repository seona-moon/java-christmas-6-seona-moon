package christmas.model.event;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import java.util.EnumMap;

public class WeekdayDiscountEventPolicy implements DiscountEventPolicy {
    private static final int DISCOUNT_PER_DESSERT = 2023;

    @Override
    public boolean isEventApplicable(int date) {
        return DayType.from(date) == DayType.WEEKDAY;
    }


    @Override
    public int getDiscountAmount(int date, EnumMap<Menu, Integer> menus) {
        if (isEventApplicable(date)) {
            return calculateTotalDiscountForDesserts(menus);
        }
        return 0;
    }

    private int calculateTotalDiscountForDesserts(EnumMap<Menu, Integer> menus) {
        return menus.entrySet().stream()
            .filter(entry -> entry.getKey().getType() == MenuType.Dessert)
            .mapToInt(entry -> entry.getValue() * DISCOUNT_PER_DESSERT)
            .sum();
    }
}
