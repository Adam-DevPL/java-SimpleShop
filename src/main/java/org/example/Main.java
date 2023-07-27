package org.example;

import org.example.Model.Discount;
import org.example.Model.ProductCategory;
import org.example.Model.ProductDto;
import org.example.service.BackofficeService;
import org.example.service.BackofficeServiceImpl;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        ProductDto apple = new ProductDto("apple", ProductCategory.FOOD, 100, 0.1, null);
        ProductDto tv = new ProductDto("tv", ProductCategory.ELECTRONICS, 200, 0.2, Discount.ELECTRONICS);
        ProductDto tennisBall = new ProductDto("tennis ball", ProductCategory.SPORT, 300, 0.3, Discount.SPORT);
        ProductDto drugs = new ProductDto("drugs", ProductCategory.HEALTH, 400, 0.4, Discount.HEALTH);
        ProductDto chair = new ProductDto("chair", ProductCategory.OTHER, 500, 0.5, Discount.OTHER);

        BackofficeService backofficeService = new BackofficeServiceImpl();

        backofficeService.addProductToShop(apple);
        backofficeService.addProductToShop(tv);
        backofficeService.addProductToShop(tennisBall);
        backofficeService.addProductToShop(drugs);
        backofficeService.addProductToShop(chair);

        backofficeService.createOrder(null);

        String product1Id = backofficeService.getProducts().get(0).getId();
        String product2Id = backofficeService.getProducts().get(1).getId();

        String orderId = backofficeService.getOrders().get(0).getId();

        backofficeService.addProductToOrder(orderId, product1Id);
        backofficeService.addProductToOrder(orderId, product2Id);
        double finalPriceForOrder = backofficeService.finishOrder(orderId);

        System.out.println("Final price for order: " + finalPriceForOrder);
    }
}