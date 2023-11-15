package christmas.model.discountevent;

public interface DiscountPolicy {
    boolean isEventApplicable(int date);

    int calculateDiscountAmount(int totalOrderAmount);

    int getDiscountAmount();
}