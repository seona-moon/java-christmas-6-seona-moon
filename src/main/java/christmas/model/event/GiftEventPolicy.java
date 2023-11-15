package christmas.model.event;

import christmas.model.menu.Menu;
import christmas.util.TotalAmountCalculator;
import java.util.EnumMap;

public class GiftEventPolicy {
    private static final int CHAMPAGNE_PRICE = 25_000;
    private static final int QUALIFYING_AMOUNT = 120_000;

    public boolean isEventApplicable(EnumMap<Menu, Integer> menus) {
        int totalAmount = TotalAmountCalculator.calculateTotalAmount(menus);
        return totalAmount>=QUALIFYING_AMOUNT;
    }

    public int getDiscountAmount(EnumMap<Menu, Integer> menus) {
        if (isEventApplicable(menus)) {
            return CHAMPAGNE_PRICE;
        }
        return 0;
    }
}
