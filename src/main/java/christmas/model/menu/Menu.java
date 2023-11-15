package christmas.model.menu;

public enum Menu {
    MushroomSoup(6_000, "양송이수프", MenuType.Appetizer),
    Tapas(5_500, "타파스", MenuType.Appetizer),
    CaesarSalad(8_000, "시저샐러드", MenuType.Appetizer),
    TBoneSteak(55_000, "티본스테이크", MenuType.Main),
    BBQRibs(54_000, "바비큐립", MenuType.Main),
    SeafoodPasta(35_000, "해산물파스타", MenuType.Main),
    ChristmasPasta(25_000, "크리스마스파스타", MenuType.Main),
    ChocolateCake(15_000, "초코케이크", MenuType.Dessert),
    IceCream(5_000, "아이스크림", MenuType.Dessert),
    ZeroCola(3_000, "제로콜라", MenuType.Beverage),
    RedWine(60_000, "레드와인", MenuType.Beverage),
    Champagne(25_000, "샴페인", MenuType.Beverage);

    private final int price;
    private final String name;
    private final MenuType type;

    Menu(int price, String name, MenuType type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public MenuType getType() {
        return type;
    }
}

