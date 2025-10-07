package org.example.backend.service;

import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    IdService idService =  mock(IdService.class);
    ProductRepository productRepository = mock(ProductRepository.class);
    ProductService service = new ProductService(productRepository, idService);

    @Test
    public void adProduct_ShouldReturnProductWithId() {
        //GIVEN
        ProductDto productDto = new ProductDto("Test", "Test", 10, 5.99);
        String id = UUID.randomUUID().toString();
        when(idService.randomId()).thenReturn(id);
        //WHEN
        Product product = service.add(productDto);
        //THEN
        assertNotNull(product.id());
    }

    @Test
    public void deleteProductById() {
        String id = "12345";
        Product product = new Product(id, "name", "description", 10, 10.99);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // WHEN
        service.deleteProductById(id);

        // THEN
        verify(productRepository).findById(id);
        verify(productRepository).deleteById(id);
    }
}