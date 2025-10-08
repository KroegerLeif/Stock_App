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
    void findProduct_ShouldReturnProduct_whenSearchIsAnId() {
        // GIVEN
        String search = "1";
        Product expectedProduct = new Product(search, "Test Product", "Test Description", 10, 9.99);
        when(productRepository.findById(search)).thenReturn(Optional.of(expectedProduct));

        // WHEN
        Product actualProduct = service.findProduct(search);

        // THEN
        verify(productRepository).findById(search);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void findProduct_ShouldReturnProduct_whenSearchIsAnExactName() {
        // GIVEN
        String search = "Exact Name";
        Product expectedProduct = new Product("1", search, "Test Description", 10, 9.99);
        when(productRepository.findById(search)).thenReturn(Optional.empty());
        when(productRepository.findByName(search)).thenReturn(Optional.of(expectedProduct));

        // WHEN
        Product actualProduct = service.findProduct(search);

        // THEN
        verify(productRepository).findById(search);
        verify(productRepository).findByName(search);
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void findProduct_ShouldReturnProduct_whenSearchContainsAValidName() {
        // GIVEN
        String search = "some other words and then TestProduct";
        String expectedName = "TestProduct";
        Product expectedProduct = new Product("1", expectedName, "Test Description", 10, 9.99);
        when(productRepository.findById(search)).thenReturn(Optional.empty());
        when(productRepository.findByName(search)).thenReturn(Optional.empty());
        when(productRepository.findByName("some")).thenReturn(Optional.empty());
        when(productRepository.findByName("other")).thenReturn(Optional.empty());
        when(productRepository.findByName("words")).thenReturn(Optional.empty());
        when(productRepository.findByName("and")).thenReturn(Optional.empty());
        when(productRepository.findByName("then")).thenReturn(Optional.empty());
        when(productRepository.findByName(expectedName)).thenReturn(Optional.of(expectedProduct));

        // WHEN
        Product actualProduct = service.findProduct(search);

        // THEN
        assertEquals(expectedProduct, actualProduct);
    }

    @Test
    void findProduct_ShouldThrowProductNotFoundException_whenNoProductMatches() {
        // GIVEN
        String search = "nothing matches this";
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        when(productRepository.findByName(anyString())).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(ProductNotFoundException.class, () -> service.findProduct(search));
    }
}
