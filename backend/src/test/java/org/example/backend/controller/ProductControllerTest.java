package org.example.backend.controller;

import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository  productRepository;

    @Test
    void updateProduct() throws Exception {

        Product product = new Product("1" , "Test" , "Test" , 5 , 4.500);
        productRepository.save(product);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/product/update/{id}" , "1")
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
}