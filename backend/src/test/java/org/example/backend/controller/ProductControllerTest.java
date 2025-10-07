package org.example.backend.controller;

import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void test_Product_if_Was_Deleted_Successful() throws Exception {

        Product product = new Product("12345", "Name", "Description", 10, 9.99);
        productRepository.save(product);

        mockMvc.perform(delete("/api/product/{id}", product.id())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Product with id " + product.id() + " was deleted"));
        assertFalse(productRepository.findById(product.id()).isPresent());
    }

    @Test
    void test_Product_if_Product_Was_Not_Found_to_Delete() throws Exception {

        //Product created, but not added to database
        Product product = new Product("123456789", "Name", "Description", 10, 9.99);

        mockMvc.perform(delete("/api/product/{id}", product.id())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andExpect(content().string("Product with id " + product.id() + " was not found"));

    }
}