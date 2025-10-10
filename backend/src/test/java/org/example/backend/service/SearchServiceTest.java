package org.example.backend.service;

import org.example.backend.expetion.ProductNotFoundException;
import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
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

    @Test
    void findProductByName_ShouldReturnProduct_whenProductExists() {
        // GIVEN
        String name = "Test Product";
        Product expectedProduct = new Product("1", name, "Test Description", 10, 9.99);
        when(productRepository.findByName(name)).thenReturn(Optional.of(expectedProduct));

        // WHEN
        Product actualProduct = service.findProductByName(name);

        // THEN
        verify(productRepository).findByName(name);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void findProductByName_ShouldThrowProductNotFoundException_whenProductDoesNotExist() {
        // GIVEN
        String name = "non-existent-name";
        when(productRepository.findByName(name)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(ProductNotFoundException.class, () -> service.findProductByName(name));
        verify(productRepository).findByName(name);
    }

    @Test
    void findProduct_ShouldReturnListOfProducts_whenProductsExist() {
        // GIVEN
        String search = "Test";
        List<Product> expectedProducts = List.of(
                new Product("1", "Test Product 1", "Description 1", 10, 9.99),
                new Product("2", "Test Product 2", "Description 2", 5, 19.99)
        );
        when(productRepository.findByNameContainingIgnoreCase(search)).thenReturn(expectedProducts);

        // WHEN
        List<Product> actualProducts = service.findProduct(search);

        // THEN
        verify(productRepository).findByNameContainingIgnoreCase(search);
        assertEquals(expectedProducts, actualProducts);
    }

    @Test
    void findProduct_ShouldReturnEmptyList_whenNoProductsMatch() {
        // GIVEN
        String search = "non-existent";
        when(productRepository.findByNameContainingIgnoreCase(search)).thenReturn(List.of());

        // WHEN
        List<Product> actualProducts = service.findProduct(search);

        // THEN
        verify(productRepository).findByNameContainingIgnoreCase(search);
        assertEquals(0, actualProducts.size());
    }
}
