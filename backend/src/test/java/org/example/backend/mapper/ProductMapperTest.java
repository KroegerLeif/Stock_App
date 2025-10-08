package org.example.backend.mapper;

import org.example.backend.dto.ProductResponse;
import org.example.backend.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

    private final ProductMapper mapper = new ProductMapper();

    @Test
    void mapProductToResponse_ShouldCorrectlyMapProductToProductResponse() {
        // GIVEN
        Product product = new Product("1", "Test Product", "A great product", 100, 19.99);

        // WHEN
        ProductResponse response = mapper.mapProductToResponse(product);

        // THEN
        assertEquals("Test Product", response.name());
        assertEquals("A great product", response.description());
        assertEquals(19.99, response.price());
    }
}