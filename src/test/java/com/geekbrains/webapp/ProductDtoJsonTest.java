package com.geekbrains.webapp;

import com.geekbrains.webapp.dtos.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ProductDtoJsonTest {
    @Autowired
    public JacksonTester<ProductDto> jacksonTester;

    @Test
    public void jsonSerializationTest() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Product");
        productDto.setPrice(100);

        assertThat(jacksonTester.write(productDto))
                .hasJsonPathNumberValue("$.id")
                .hasJsonPathNumberValue("$.price")
                .extractingJsonPathStringValue("$.title").isEqualTo("Product");
    }

    @Test
    public void jsonDeserializationTest() throws Exception {
        String jsonProductDto = "{\"id\":2,\"title\":\"Product\"}";
        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setTitle("Product");

        assertThat(jacksonTester.parse(jsonProductDto)).isEqualTo(productDto);
    }

}
