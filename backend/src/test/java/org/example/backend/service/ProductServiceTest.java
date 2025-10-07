package org.example.backend.service;

import org.example.backend.expetion.ProductNotFoundException;
import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
        Mockito.when(idService.randomId()).thenReturn(id);
        //WHEN
        Product product = service.add(productDto);
        //THEN
        assertNotNull(product.id());
    }

    @Test
    void findProductById_ShouldReturnProduct_whenProductExists() {
        // GIVEN
        String id = "1";
        Product expectedProduct = new Product(id, "Test Product", "Test Description", 10, 9.99);
        when(productRepository.findById(id)).thenReturn(Optional.of(expectedProduct));

        // WHEN
        Product actualProduct = service.findProductById(id);

        // THEN
        verify(productRepository).findById(id);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void findProductById_ShouldThrowProductNotFoundException_whenProductDoesNotExist() {
        // GIVEN
        String id = "non-existent-id";
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(ProductNotFoundException.class, () -> service.findProductById(id));
        verify(productRepository).findById(id);
    }
}
