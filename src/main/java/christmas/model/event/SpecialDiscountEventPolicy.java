package christmas.model.event;

import christmas.model.menu.Menu;
import java.util.EnumMap;

public class SpecialDiscountEventPolicy implements DiscountEventPolicy{

    private static final int SPECIAL_DISCOUNT = 1000;

    @Override
    public boolean isEventApplicable(int date) {
        return isSpecialDay(date);
    }

    @Override
    public int getDiscountAmount(int date, EnumMap<Menu, Integer> menus) {
        if (isEventApplicable(date)) {
            return SPECIAL_DISCOUNT;
        }
        return 0;
    }

    private static boolean isSpecialDay(int date) {
        int dayOfWeek = date % DayConstant.WEEK_SIZE;
        if (dayOfWeek==DayConstant.SUNDAY || date == DayConstant.CHRISTMAS_DAY) {
            return true;
        }
        return false;
    }
}
