package christmas.model.event;

public enum DayType {
    WEEKDAY,
    WEEKEND;

    public static DayType from(int date) {
        int dayOfWeek = date % 7;
        switch (dayOfWeek) {
            case 1:
            case 2:
                return WEEKEND;
            case 0:
            case 3:
            case 4:
            case 5:
            case 6:
                return WEEKDAY;
            default:
                throw new IllegalArgumentException();
        }
    }
}

