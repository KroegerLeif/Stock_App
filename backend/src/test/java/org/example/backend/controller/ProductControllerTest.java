package org.example.backend.controller;

import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMockRestServiceServer
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void addProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                   "name": "Test",
                                   "description": "Test",
                                   "stock": 10,
                                   "price": 9.99
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    void test_Product_if_Was_Deleted_Successful() throws Exception {

        Product product = new Product("12345", "Name", "Description", 10, 9.99);
        productRepository.save(product);

        mockMvc.perform(delete("/api/products/{id}", product.id())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Product with id " + product.id() + " was deleted"));
        assertFalse(productRepository.findById(product.id()).isPresent());
    }


    @Test
    void test_Product_if_Product_Was_Not_Found_to_Delete() throws Exception {

        //Product created, but not added to database
        Product product = new Product("123456789", "Name", "Description", 10, 9.99);

        mockMvc.perform(delete("/api/products/{id}", product.id())
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isNotFound())
                        .andExpect(content().string("Product with id " + product.id() + " was not found"));

    }
    @Test
    void updateProduct() throws Exception {

        Product product = new Product("1" , "Test" , "Test" , 5 , 4.500);
        productRepository.save(product);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/update/{id}" , "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  {
                                    "name" : "Test",
                                    "description" : "Test",
                                    "stock" : 10,
                                    "price" : 4.500
                                   }
                                 """)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                 
                                   {
                                     "id" : "1",
                                     "name" : "Test",
                                     "description" : "Test",
                                     "stock" : 10,
                                     "price" : 4.500
                                   }
                                   
                                   """
                ));
    }

    @Test
    void findProductById_ShouldReturnProduct_whenProductExists() throws Exception {
        //GIVEN
        Product product = new Product("1" , "Test" , "Test" , 5 , 4.500);
        productRepository.save(product);

        //WHEN & THEN
        mockMvc.perform(get("/api/products/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id": "1",
                        "name": "Test",
                        "description": "Test",
                        "price": 4.500
                    }
                """));
    }

    @Test
    void findProductById_ShouldReturnNotFound_whenProductDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/products/{id}", "non-existent-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void searchForProduct_ShouldReturnProduct_whenProductExists() throws Exception {
        //GIVEN
        Product product = new Product("1" , "Test" , "Test" , 5 , 4.500);
        productRepository.save(product);

        //WHEN & THEN
        mockMvc.perform(get("/api/products/search/{search}", "Test"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    [
                        {
                            "id": "1",
                            "name": "Test",
                            "description": "Test",
                            "price": 4.500
                        }
                    ]
                """));
    }

    @Test
    void searchForProduct_ShouldReturnEmptyList_whenProductDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/products/search/{search}", "non-existent-name"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

}