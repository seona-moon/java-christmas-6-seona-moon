package christmas.view;

public enum ErrorMessage {
    EMPTY_VALUE_ERROR("공백 또는 빈 문자열은 입력할 수 없습니다."),
    INVALID_MENU_ERROR("유효하지 않은 주문입니다."),
    DUPLICATE_MENU_ERROR("중복 메뉴 주문입니다."),
    BEVERAGE_ONLY_ERROR("음료만 주문할 수 없습니다."),
    MAX_ITEM_COUNT_EXCEEDED_ERROR("한 번에 최대 20개까지만 주문할 수 있습니다."),
    INVALID_DATE_ERROR("유효하지 않은 날짜입니다.");

    private static final String PREFIX = "[ERROR] ";
    private static final String SUFFIX = " 다시 입력해 주세요.";

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message + SUFFIX;
    }
}
