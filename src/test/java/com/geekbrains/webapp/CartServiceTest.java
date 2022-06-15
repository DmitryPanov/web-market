package com.geekbrains.webapp;

import com.geekbrains.webapp.model.Category;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.services.CartService;
import com.geekbrains.webapp.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void initCart() {
        cartService.clearCart(() -> "testCart", "test_cart");
    }

    @Test
    public void addToCart() {
        Category category = new Category();
        category.setId(1L);

        Product product = new Product();
        product.setId(10L);
        product.setTitle("Product");
        product.setCategory(category);
        product.setPrice(100);


        Mockito.doReturn(Optional.of(product)).when(productService).findById(10L);

    }
}
