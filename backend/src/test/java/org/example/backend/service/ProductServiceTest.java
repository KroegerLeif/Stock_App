package org.example.backend.service;

import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class ProductServiceTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void deleteProductById(){

        Product product = new Product("12345", "name", "description", 10, 10.99);
        Product saved = productRepository.save(product);

        productRepository.deleteById(saved.id());
        assertTrue(productRepository.findById(saved.id()).isEmpty());

    }
}