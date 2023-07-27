package org.example.Model;

public class ProductDto {
    private final String name;

    private final ProductCategory category;

    private final int price;

    private final double tax;

    private final Discount discount;

    public ProductDto(String name, ProductCategory category, int price, double tax, Discount discount) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.tax = tax;
        this.discount = discount == null ? Discount.NONE : discount;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public double getTax() {
        return tax;
    }

    public Discount getDiscount() {
        return discount;
    }
}
