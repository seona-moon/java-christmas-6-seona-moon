package christmas.model.event;

public enum DayType {
    WEEKDAY,
    WEEKEND;

    public static DayType from(int date) {
        int dayOfWeek = date % DayConstant.WEEK_SIZE;
        switch (dayOfWeek) {
            case DayConstant.FRIDAY:
            case DayConstant.SATURDAY:
                return WEEKEND;
            case DayConstant.THURSDAY:
            case DayConstant.MONDAY:
            case DayConstant.TUESDAY:
            case DayConstant.WEDNESDAY:
                return WEEKDAY;
            default:
                throw new IllegalArgumentException();
        }
    }
}

