package com.geekbrains.webapp;

import com.geekbrains.webapp.dtos.ProductDto;
import com.geekbrains.webapp.model.Category;
import com.geekbrains.webapp.model.Product;
import com.geekbrains.webapp.repositories.ProductRepository;
import com.geekbrains.webapp.services.CategoryService;
import com.geekbrains.webapp.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void updateProductDto() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");

        Product product =new Product();
        product.setId(1L);
        product.setPrice(100);
        product.setTitle("Product");
        product.setCategory(category);

        Optional<Product> productOptional = Optional.of(product);
        Mockito.doReturn(productOptional).when(productRepository).findById(1L);

        ProductDto productDto = new ProductDto(product);
        productDto.setCategoryTitle("Non-Food");

        Category category1 = new Category();
        category1.setId(2L);
        category1.setTitle("Non-Food");

        Optional<Category> categoryOptional = Optional.of(category1);
        Mockito.doReturn(categoryOptional).when(categoryService).findByTitle("Non-Food");

        productService.updateProductFromDto(productDto);
        Assertions.assertEquals("Non-Food",product.getCategory().getTitle());
    }
}
