package com.example.productapi.controller;

import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void testGetProductsByPriceRange() throws Exception {
        Product product1 = new Product("74001755", "Ball Gown", "Full Body Outfits", 3548, 7, 1);
        Product product2 = new Product("74002423", "Shawl", "Accessories", 758, 12, 1);
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.findByPriceRange(500, 4000)).thenReturn(products);

        mockMvc.perform(get("/price/500/4000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].item").value("Ball Gown"))
                .andExpect(jsonPath("$[1].item").value("Shawl"));
    }

    @Test
    public void testGetAllProductsSortedByPrice() throws Exception {
        List<String> sortedProducts = Arrays.asList("Shawl", "Ball Gown");

        when(productService.findAllSortedByPrice()).thenReturn(sortedProducts);

        mockMvc.perform(get("/price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Shawl"))
                .andExpect(jsonPath("$[1]").value("Ball Gown"));
    }

    @Test
    public void testInvalidPriceRange() throws Exception {
        mockMvc.perform(get("/price/5000/1000"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testNegativePriceRange() throws Exception {
        mockMvc.perform(get("/price/-100/500"))
                .andExpect(status().isBadRequest());
    }
}