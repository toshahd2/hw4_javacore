package hw4;

public enum Discount {
    ZERO(0),
    FIVE(5),
    TEN(10),
    FIFTEEN(15),
    TWENTY(20);

    private final int value;
    Discount(int value) {
        this.value =  value;
    }

    public int getValue() {
        return value;
    }
}
