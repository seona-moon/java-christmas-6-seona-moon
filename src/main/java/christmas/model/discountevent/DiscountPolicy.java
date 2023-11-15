package christmas.model.event;

import java.time.LocalDate;

public interface DiscountPolicy {
    boolean isEventApplicable(int date);

    int calculateDiscountAmount(int totalOrderAmount);

    int getDiscountAmount();
}