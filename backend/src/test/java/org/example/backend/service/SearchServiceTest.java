package org.example.backend.service;

import org.example.backend.expetion.ProductNotFoundException;
import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SearchServiceTest {
    ProductRepository productRepository = mock(ProductRepository.class);
    SearchService service = new SearchService(productRepository);

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
