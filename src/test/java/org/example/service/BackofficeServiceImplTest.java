package org.example.service;

import org.example.Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackofficeServiceImplTest {

    private BackofficeService backofficeService;

    @BeforeEach
    public void setUp() {
        backofficeService = new BackofficeServiceImpl();
    }

    @Test
    public void testAddProductToShop() {
        ProductDto productDto = new ProductDto("Product1", ProductCategory.FOOD, 100, 0.1, Discount.FOOD);
        backofficeService.addProductToShop(productDto);
        List<Product> products = backofficeService.getProducts();
        assertEquals(1, products.size());
        assertEquals("Product1", products.get(0).getName());
        assertEquals(ProductCategory.FOOD, products.get(0).getCategory());
    }

    @Test
    public void testCreateOrder() {
        backofficeService.createOrder(Discount.HEALTH);
        List<Order> orders = backofficeService.getOrders();
        assertEquals(1, orders.size());
    }

    @Test
    public void testAddProductToOrder() {
        ProductDto productDto = new ProductDto("Product1", ProductCategory.FOOD, 100, 0.1, Discount.FOOD);
        backofficeService.addProductToShop(productDto);
        backofficeService.createOrder(Discount.NONE);
        List<Product> products = backofficeService.getProducts();
        List<Order> orders = backofficeService.getOrders();
        assertEquals(1, products.size());
        assertEquals(1, orders.size());

        backofficeService.addProductToOrder(orders.get(0).getId(), products.get(0).getId());
        Order order = backofficeService.getOrders().get(0);
        assertEquals(1, order.getProducts().size());
        assertEquals("Product1", order.getProducts().get(0).getName());
    }

    @Test
    public void testFinishOrder() {
        ProductDto productDto = new ProductDto("Product1", ProductCategory.FOOD, 100, 0.1, Discount.FOOD);
        backofficeService.addProductToShop(productDto);
        backofficeService.createOrder(Discount.NONE);
        backofficeService.addProductToOrder(backofficeService.getOrders().get(0).getId(), backofficeService.getProducts().get(0).getId());

        double finalPrice = backofficeService.finishOrder(backofficeService.getOrders().get(0).getId());
        assertEquals(100.0, finalPrice); // Assuming no discounts applied in this test
        assertEquals(0, backofficeService.getOrders().size());
        assertEquals(1, backofficeService.getFinishedOrders().size());
    }

}
