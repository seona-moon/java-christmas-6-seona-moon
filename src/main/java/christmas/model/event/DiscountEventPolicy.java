package christmas.model.event;

import christmas.model.menu.Menu;
import java.util.EnumMap;

public interface DiscountEventPolicy {
    boolean isEventApplicable(int date);
    int getDiscountAmount(int date, EnumMap<Menu, Integer> menus);
}