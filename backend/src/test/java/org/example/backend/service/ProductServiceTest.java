package org.example.backend.service;

import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductServiceTest {

    IdService idService =  mock(IdService.class);
    ProductRepository productRepository = mock(ProductRepository.class);
    ProductService service = new ProductService(productRepository, idService);

    @Test
    public void adProduct_ShouldReturnProductWithId() {
        //GIVEN
        ProductDto productDto = new ProductDto("Test", "Test", 10, 5.99);
        String id = UUID.randomUUID().toString();
        Mockito.when(idService.randomId()).thenReturn(id);
        //WHEN
        Product product = service.add(productDto);
        //THEN
        assertNotNull(product.id());
    }

}