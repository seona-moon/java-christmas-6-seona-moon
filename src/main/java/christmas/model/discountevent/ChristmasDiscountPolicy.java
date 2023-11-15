package christmas.model.discountevent;

import christmas.validator.DateValidator;

public class ChristmasDiscountPolicy implements DiscountPolicy {
    private static final int START_DAY = 1;
    private static final int CHRISTMAS_DAY = 25;
    private static final int INITIAL_DISCOUNT = 1000;
    private int discountAmount;
    private int date;

    public ChristmasDiscountPolicy(int date) {
        this.discountAmount = INITIAL_DISCOUNT;
        DateValidator.validateDate(date);
        this.date = date;
    }

    @Override
    public boolean isEventApplicable(int date) {
        //이벤트 적용 여부를 파악
        return START_DAY <= date && date <= CHRISTMAS_DAY;
    }

    public int calculateDiscountAmount(int totalOrderAmount) {
        // 이벤트 적용 시
        if (isEventApplicable(date)) {
            int daysPassed = date - START_DAY; // 현재 날짜에서 시작일까지의 날짜 수 계산
            int currentDiscount = INITIAL_DISCOUNT + daysPassed * 100; // 현재 할인 금액 계산
            return Math.min(currentDiscount, totalOrderAmount);
        }
        // 이벤트가 적용되지 않을 때는 할인 없음을 반환
        return 0;
    }

    @Override
    public int getDiscountAmount() {
        return discountAmount;
    }
}
