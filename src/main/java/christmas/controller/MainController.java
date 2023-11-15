package christmas.controller;

import static christmas.model.event.GiftEventPolicy.CHAMPAGNE_PRICE;
import static christmas.util.TotalAmountCalculator.calculateTotalAmount;
import static christmas.util.TotalAmountCalculator.calculateTotalDiscount;

import christmas.model.event.EventManager;
import christmas.model.menu.Menu;
import christmas.model.menu.OrderMenu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private EventManager eventManager;

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.displayWelcomeMessage();
        int visitDate = receiveValidVisitDate();
        EnumMap<Menu, Integer> orderedMenus = receiveValidOrderMenu();
        outputView.displayEventPreview(visitDate);
        processOrder(visitDate, orderedMenus);
    }

    private int receiveValidVisitDate() {
        while (true) {
            try {
                outputView.displayVisitDatePrompt();
                int visitDate = inputView.getVisitDate();
                eventManager = new EventManager(visitDate);
                return visitDate;
            } catch (NumberFormatException e) {
                outputView.displayNumberFormatError(e);
            } catch (IllegalArgumentException e) {
                outputView.displayArgumentError(e);
            }
        }
    }

    private EnumMap<Menu, Integer> receiveValidOrderMenu() {
        while (true) {
            try {
                outputView.displayMenuOrderPrompt();
                String orderInput = inputView.getOrderMenu();
                return new OrderMenu(orderInput).getMenus();
            } catch (IllegalArgumentException e) {
                outputView.displayArgumentError(e);
            }
        }
    }

    private void processOrder(int visitDate, EnumMap<Menu, Integer> orderedMenus) {
        outputView.displayOrderMenu(orderedMenus);
        int totalOrderAmount = calculateTotalAmount(orderedMenus);
        outputView.displayTotalOrderAmount(totalOrderAmount);
        Map<String, Integer> benefits = eventManager.calculateEventsResults(visitDate, orderedMenus);
        outputView.displayGiftMenu(benefits);
        outputView.displayDiscountDetails(benefits);
        int totalDiscount = calculateTotalDiscount(benefits);
        outputView.displayTotalDiscountAmount(totalDiscount);
        int expectedPayment = totalOrderAmount - calculateExpectedDiscount(totalDiscount,benefits);
        outputView.displayExpectedPaymentAmount(expectedPayment);
        String badge = eventManager.getBadge();
        outputView.displayEventBadge(badge);
    }

    private int calculateExpectedDiscount(int totalDiscount, Map<String, Integer> benefits) {
        if (benefits.containsKey("증정 이벤트")) {
            totalDiscount -= CHAMPAGNE_PRICE;
        }
        return totalDiscount;
    }
}
