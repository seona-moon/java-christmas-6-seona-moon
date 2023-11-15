package christmas.model.event;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import java.util.EnumMap;

public class WeekendDiscountEventPolicy implements DiscountEventPolicy{

    private static final int DISCOUNT_PER_MAIN = 2023;

    @Override
    public boolean isEventApplicable(int date) {
        return DayType.from(date) == DayType.WEEKEND;
    }

    @Override
    public int getDiscountAmount(int date, EnumMap<Menu, Integer> menus) {
        if (isEventApplicable(date)) {
            return calculateTotalDiscountForMains(menus);
        }
        return 0;
    }

    private int calculateTotalDiscountForMains(EnumMap<Menu, Integer> menus) {
        return menus.entrySet().stream()
            .filter(entry -> entry.getKey().getType() == MenuType.Main)
            .mapToInt(entry -> entry.getValue() * DISCOUNT_PER_MAIN)
            .sum();
    }

}
