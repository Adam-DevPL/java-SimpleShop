package org.example.Model;

import java.util.UUID;

public class Product {

    private final String id;

    private final String name;

    private final ProductCategory category;

    private final int price;

    private final double tax;

    private final Discount discount;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getFinalPrice() {
        return price + (int) (price * tax) - (int) (price * discount.getDiscount());
    }

    private Product(ProductBuilder productDto) {
        this.id = UUID.randomUUID().toString();
        this.name = productDto.name;
        this.category = productDto.category;
        this.price = productDto.price;
        this.tax = productDto.tax;
        this.discount = productDto.discount;
    }

    public static class ProductBuilder {

        private final String name;

        private final ProductCategory category;

        private final int price;

        private final double tax;

        private final Discount discount;

        public ProductBuilder(ProductDto productDto) {
            this.name = productDto.getName();
            this.category = productDto.getCategory();
            this.price = productDto.getPrice();
            this.tax = productDto.getTax();
            this.discount = productDto.getDiscount();
        }

        public Product build() {
            return new Product(this);
        }
    }
}
