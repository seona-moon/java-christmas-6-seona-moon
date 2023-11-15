package christmas.model.event;

import christmas.model.menu.Menu;
import christmas.validator.DateValidator;
import java.util.EnumMap;

public class ChristmasDiscountEventPolicy implements DiscountEventPolicy {
    private static final int START_DAY = 1;
    private static final int CHRISTMAS_DAY = 25;
    private static final int INITIAL_DISCOUNT = 1000;
    private static final int DISCOUNT_INCREMENT_PER_DAY = 100;


    @Override
    public boolean isEventApplicable(int date) {
        return START_DAY <= date && date <= CHRISTMAS_DAY;
    }

    @Override
    public int getDiscountAmount(int date, EnumMap<Menu, Integer> menus) {
        if (isEventApplicable(date)) {
            int daysPassed = date - START_DAY;
            int discountAmount = INITIAL_DISCOUNT + daysPassed * DISCOUNT_INCREMENT_PER_DAY;
            return discountAmount;
        }
        return 0;
    }
}
