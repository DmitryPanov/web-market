package com.geekbrains.webapp;

import com.geekbrains.webapp.dtos.OrderItemDto;
import com.geekbrains.webapp.model.Category;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.utils.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CartTest {

    private Cart cart;
    private OrderItemDto orderItemDto1;
    private OrderItemDto orderItemDto2;
    private List<OrderItemDto> orderItemDtoList;

    @BeforeEach
    public void init() {
        cart = new Cart();
    }

    @BeforeEach
    public void initProduct() {
        orderItemDto1 = new OrderItemDto();
        orderItemDto1.setProductId(1L);
        orderItemDto1.setQuantity(3);
        orderItemDto1.setPricePerProduct(50);
        orderItemDto1.setPrice(150);
        orderItemDto1.setProductTitle("Bread");


        orderItemDto2 = new OrderItemDto();
        orderItemDto2.setProductId(2L);
        orderItemDto2.setQuantity(5);
        orderItemDto2.setPricePerProduct(400);
        orderItemDto2.setPrice(2000);
        orderItemDto2.setProductTitle("Cheese");

        orderItemDtoList = new ArrayList<>();
        orderItemDtoList.add(orderItemDto1);
        orderItemDtoList.add(orderItemDto2);
        cart.setItems(orderItemDtoList);
    }

    @Test
    public void increment() {
        cart.decrement(2L);
        cart.decrement(1L);

        Assertions.assertEquals(1700, cart.getTotalPrice());

    }

    @Test
    public void incrementMoreQuantity() {

        cart.decrement(1L);
        cart.decrement(1L);
        cart.decrement(1L);
        cart.decrement(1L);
        cart.decrement(1L);

        Assertions.assertEquals(2000, cart.getTotalPrice());
    }

    @Test
    public void addQuantity() {
        cart.add(1L);
        cart.add(1L);
        cart.add(1L);
        cart.add(1L);

        Assertions.assertEquals(2350, cart.getTotalPrice());
        Assertions.assertEquals(orderItemDto1, cart.getItems().get(0));
    }

    @Test
    public void addOrderItem() {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setProductId(3L);
        orderItemDto.setQuantity(3);
        orderItemDto.setPricePerProduct(100);
        orderItemDto.setPrice(100);
        orderItemDto.setProductTitle("Beer");
        orderItemDtoList.add(orderItemDto);

        Assertions.assertEquals(cart.getItems().get(2), orderItemDto);
    }

    @Test
    public void merge() {
        Cart newCart = new Cart();
        Product product = new Product();
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");

        product.setId(10L);
        product.setPrice(100);
        product.setTitle("Beer");
        product.setCategory(category);

        newCart.add(product);
        Assertions.assertEquals(100, newCart.getTotalPrice());

        cart.merge(newCart);
        Assertions.assertEquals(2250, cart.getTotalPrice());

    }

    @Test
    public void addProduct() {
        Product product = new Product();
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");

        product.setId(10L);
        product.setPrice(100);
        product.setTitle("Beer");
        product.setCategory(category);

        cart.add(product);
        Assertions.assertEquals(100, cart.getItems().get(2).getPrice());
    }
}
