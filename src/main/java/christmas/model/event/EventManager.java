package christmas.model.event;

import christmas.model.menu.Menu;
import christmas.util.TotalAmountCalculator;
import christmas.view.ErrorMessage;
import java.util.EnumMap;
import java.util.Map;
import java.util.LinkedHashMap;

public class EventManager {

    private static final int STAR_BADGE_THRESHOLD = 5_000;
    private static final int TREE_BADGE_THRESHOLD = 10_000;
    private static final int SANTA_BADGE_THRESHOLD = 20_000;
    private static final int EVENT_THRESHOLD = 10_000;

    private final Map<String, DiscountEventPolicy> discountPolicies;
    private final GiftEventPolicy giftPolicy;
    private int totalDiscountAmount;
    private String badge;

    public EventManager(int date) {
        validateDate(date);
        this.discountPolicies = new LinkedHashMap<>();
        this.discountPolicies.put("크리스마스 디데이 할인", new ChristmasDiscountEventPolicy());
        this.discountPolicies.put("평일 할인", new WeekdayDiscountEventPolicy());
        this.discountPolicies.put("주말 할인", new WeekendDiscountEventPolicy());
        this.discountPolicies.put("특별 할인", new SpecialDiscountEventPolicy());
        this.giftPolicy = new GiftEventPolicy();
    }

    public Map<String, Integer> calculateEventsResults(int date, EnumMap<Menu, Integer> menus) {
        Map<String, Integer> eventResults = new LinkedHashMap<>();
        if(TotalAmountCalculator.calculateTotalAmount(menus)<EVENT_THRESHOLD) {
            return eventResults;
        }
        totalDiscountAmount = 0;
        totalDiscountAmount += applyDiscountPolicies(date, menus, eventResults);
        totalDiscountAmount += applyGiftPolicy(menus, eventResults);
        badge = determineBadge(totalDiscountAmount);

        return eventResults;
    }

    public String getBadge() {
        return badge;
    }

    private int applyDiscountPolicies(int date, EnumMap<Menu, Integer> menus,
        Map<String, Integer> eventResults) {
        int totalDiscount = 0;
        for (Map.Entry<String, DiscountEventPolicy> entry : discountPolicies.entrySet()) {
            String eventName = entry.getKey();
            DiscountEventPolicy policy = entry.getValue();
            if (policy.isEventApplicable(date)) {
                int discountAmount = policy.getDiscountAmount(date, menus);
                totalDiscount += discountAmount;
                eventResults.put(eventName, discountAmount);
            }
        }
        return totalDiscount;
    }

    private int applyGiftPolicy(EnumMap<Menu, Integer> menus, Map<String, Integer> eventResults) {
        if (giftPolicy.isEventApplicable(menus)) {
            int giftAmount = giftPolicy.getDiscountAmount(menus);
            eventResults.put("증정 이벤트", giftAmount);
            return giftAmount;
        }
        return 0;
    }


    private String determineBadge(int totalDiscountAmount) {
        if (totalDiscountAmount >= SANTA_BADGE_THRESHOLD) {
            return "산타";
        }
        if (totalDiscountAmount >= TREE_BADGE_THRESHOLD) {
            return "트리";
        }
        if (totalDiscountAmount >= STAR_BADGE_THRESHOLD) {
            return "별";
        }
        return "없음";
    }

    private static void validateDate(int date) throws IllegalArgumentException {
        if (date < DayConstant.START_DAY || date > DayConstant.END_DAY) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE_ERROR.getMessage());
        }
    }
}
