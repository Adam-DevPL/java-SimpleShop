package org.example.Model;

public enum Discount {
    NONE(0),
    FOOD(0.1),
    ELECTRONICS(0.2),
    SPORT(0.3),
    HEALTH(0.4),
    OTHER(0.5);

    private final double discount;

    Discount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}
