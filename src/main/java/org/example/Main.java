package org.example;

import org.example.Model.ProductCategory;
import org.example.service.BackofficeService;
import org.example.service.BackofficeServiceImpl;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        BackofficeService backofficeService = new BackofficeServiceImpl();

        backofficeService.addDiscount("discount0", 0);
        backofficeService.addDiscount("discount1", 0.1);
        backofficeService.addDiscount("discount2", 0.2);

        backofficeService.addProductToShop(0, "apple", ProductCategory.FOOD, 100, 0.1, backofficeService.getDiscount("discount0"));
        backofficeService.addProductToShop(1, "tv", ProductCategory.ELECTRONICS, 200, 0.2, backofficeService.getDiscount("discount1"));
        backofficeService.addProductToShop(2, "tennis ball", ProductCategory.SPORT, 300, 0.3, backofficeService.getDiscount("discount0"));
        backofficeService.addProductToShop(3, "drugs", ProductCategory.HEALTH, 400, 0.4, backofficeService.getDiscount("discount2"));
        backofficeService.addProductToShop(4, "chair", ProductCategory.OTHER, 500, 0.5, backofficeService.getDiscount("discount1"));

        backofficeService.createOrder(0, backofficeService.getDiscount("discount0"));
        backofficeService.addProductToOrder(0, 0);
        backofficeService.addProductToOrder(0, 4);
        int finalPriceForOrder = backofficeService.finishOrder(0);

        System.out.println("Final price for order: " + finalPriceForOrder);
    }
}