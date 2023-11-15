package christmas.view;

public enum OutputMessage {
    WELCOME_PROMPT("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    VISIT_DAY_PROMPT("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    ORDER_MENU_PROMPT("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    ORDER_MENU_OUTPUT("<주문 메뉴>"),
    ORDER_AMOUNT_BEFORE_DISCOUNT_OUTPUT("<할인 전 총주문 금액>"),
    GIFT_MENU_OUTPUT("<증정 메뉴>"),
    DISCOUNT_DETAIL_OUTPUT("<혜택 내역>"),
    TOTAL_DISCOUNT_AMOUNT("<총혜택 금액>"),
    ORDER_AMOUNT_AFTER_DISCOUNT_OUTPUT("<할인 후 예상 결제 금액>"),
    EVENT_BADGE_OUTPUT("<12월 이벤트 배지>");
    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}

