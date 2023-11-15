package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.view.ErrorMessage.EMPTY_VALUE_ERROR;

public class InputView {
    public int getVisitDate() {
        String input = readLine().trim();
        validateEmptyValue(input);
        return Integer.parseInt(input);
    }

    public String getOrderMenu() {
        String input = readLine().trim();
        validateEmptyValue(input);
        return input;
    }

    public void validateEmptyValue(String value) throws IllegalArgumentException {
        if (value.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_VALUE_ERROR.getMessage());
        }
    }
}
