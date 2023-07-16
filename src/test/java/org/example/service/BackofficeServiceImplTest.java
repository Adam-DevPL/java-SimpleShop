package org.example.service;

import org.example.Model.Order;
import org.example.Model.Product;
import org.example.Model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BackofficeServiceImplTest {
    private BackofficeService backofficeService;

    @BeforeEach
    void setUp() {
        backofficeService = new BackofficeServiceImpl();
    }

    @Test
    void addProductToShop_shouldAddProductToShop() {
        // Given
        int id = 1;
        String name = "Product 1";
        ProductCategory category = ProductCategory.FOOD;
        int price = 10;
        double tax = 0.1;
        double discount = 0.2;

        // When
        backofficeService.addProductToShop(id, name, category, price, tax, discount);

        // Then
        Product product = backofficeService.getProducts().get(0);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(category, product.getCategory());
    }

    @Test
    void addDiscount_shouldAddDiscount() {
        // Given
        String name = "Product 1";
        double discount = 0.2;

        // When
        backofficeService.addDiscount(name, discount);

        // Then
        double actualDiscount = backofficeService.getDiscount(name);
        assertEquals(discount, actualDiscount, 0.0001);
    }

    @Test
    void createOrder_shouldCreateOrder() {
        // Given
        int id = 1;
        double discount = 0.1;

        // When
        backofficeService.createOrder(id, discount);

        // Then
        Order order = backofficeService.getOrders().get(0);
        assertEquals(id, order.getId());
        assertEquals(discount, order.getDiscount(), 0.0001);
        assertTrue(order.getProducts().isEmpty());
    }

    @Test
    void addProductToOrder_shouldAddProductToOrder() {
        // Given
        int orderId = 1;
        int productId = 1;
        backofficeService.createOrder(orderId, 0.1);
        backofficeService.addProductToShop(productId, "Product 1", ProductCategory.FOOD, 10, 0.1, 0.2);

        // When
        backofficeService.addProductToOrder(orderId, productId);

        // Then
        Order order = backofficeService.getOrders().get(0);
        assertEquals(1, order.getProducts().size());
        assertEquals(productId, order.getProducts().get(0).getId());
    }

    @Test
    void finishOrder_shouldFinishOrderAndReturnFinalPrice() {
        // Given
        int orderId = 1;
        backofficeService.createOrder(orderId, 0.1);
        backofficeService.addProductToShop(1, "Product 1", ProductCategory.FOOD, 10, 0.1, 0.2);
        backofficeService.addProductToOrder(orderId, 1);

        // When
        int finalPrice = backofficeService.finishOrder(orderId);

        // Then
        assertEquals(9, finalPrice);
        assertTrue(backofficeService.getOrders().isEmpty());
        assertEquals(1, backofficeService.getFinishedOrders().size());
        assertEquals(orderId, backofficeService.getFinishedOrders().get(0).getId());
    }
}
