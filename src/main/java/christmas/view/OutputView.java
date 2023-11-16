package christmas.view;

import static christmas.view.OutputMessage.NO_DISCOUNT;

import christmas.model.menu.Menu;
import java.text.NumberFormat;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    private static final String ITEM_SUFFIX = "개";
    private static final String CURRENCY_SUFFIX = "원";

    public void displayWelcomeMessage() {
        println(OutputMessage.WELCOME_PROMPT.getMessage());
    }

    public void displayVisitDatePrompt() {
        println(OutputMessage.VISIT_DAY_PROMPT.getMessage());
    }

    public void displayMenuOrderPrompt() {
        println(OutputMessage.ORDER_MENU_PROMPT.getMessage());
    }

    public void displayEventPreview(int date) {
        println(OutputMessage.EVENT_PREVIEW.getMessage(date));
    }

    public void displayOrderMenu(EnumMap<Menu, Integer> orderedMenus) {
        ;
        println(OutputMessage.ORDER_MENU_OUTPUT.getMessage());
        orderedMenus.forEach((menu, quantity) ->
            println(menu.getName() + " " + quantity + ITEM_SUFFIX)
        );
        println("");
    }

    public void displayTotalOrderAmount(int totalOrderAmount) {
        println(OutputMessage.ORDER_AMOUNT_BEFORE_DISCOUNT_OUTPUT.getMessage());
        println(formatCurrency(totalOrderAmount) + CURRENCY_SUFFIX + "\n");
    }

    public void displayGiftMenu(Map<String, Integer> benefits) {
        println(OutputMessage.GIFT_MENU_OUTPUT.getMessage());
        if (benefits.containsKey("증정 이벤트")) {
            println(OutputMessage.GIFT_MENU_DETAIL.getMessage(1) + "\n");
            return;
        }
        println(NO_DISCOUNT.getMessage() + "\n");
    }

    public void displayDiscountDetails(Map<String, Integer> benefits) {
        println(OutputMessage.DISCOUNT_DETAIL_OUTPUT.getMessage());
        if(benefits.isEmpty()){
            println(NO_DISCOUNT.getMessage() + "\n");
            return;
        }
        benefits.forEach((event, amount) -> {
            println(event + ": -" + formatCurrency(amount) + CURRENCY_SUFFIX);
        });
        println("");
    }

    public void displayTotalDiscountAmount(int totalDiscount) {
        println(OutputMessage.TOTAL_DISCOUNT_AMOUNT.getMessage());
        if(totalDiscount==0){
            println(NO_DISCOUNT.getMessage() + "\n");
            return;
        }
        println("-" + formatCurrency(totalDiscount) + CURRENCY_SUFFIX + "\n");
    }

    public void displayExpectedPaymentAmount(int expectedPayment) {
        println(OutputMessage.ORDER_AMOUNT_AFTER_DISCOUNT_OUTPUT.getMessage());
        println(formatCurrency(expectedPayment) + CURRENCY_SUFFIX + "\n");
    }

    public void displayEventBadge(String badge) {
        println(OutputMessage.EVENT_BADGE_OUTPUT.getMessage());
        println(badge);
    }

    public void displayArgumentError(IllegalArgumentException e) {
        println(e.getMessage());
    }

    public void displayNumberFormatError(NumberFormatException e) {
        println(ErrorMessage.INVALID_DATE_ERROR.getMessage());
    }

    private String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.KOREA);
        return formatter.format(amount);
    }

    private void println(String message) {
        System.out.println(message);
    }
}
