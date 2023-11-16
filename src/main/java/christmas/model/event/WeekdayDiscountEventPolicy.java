package christmas.model.event;

import static christmas.model.event.DayConstant.DISCOUNT_YEAR;

import christmas.model.menu.Menu;
import christmas.model.menu.MenuType;
import java.util.EnumMap;

public class WeekdayDiscountEventPolicy implements DiscountEventPolicy {

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
            .mapToInt(entry -> entry.getValue() * DISCOUNT_YEAR)
            .sum();
    }
}
