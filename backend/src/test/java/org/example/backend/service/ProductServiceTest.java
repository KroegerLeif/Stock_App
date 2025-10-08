package org.example.backend.service;

import org.example.backend.expetion.ProductNotFoundException;
import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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
    void updateProduct_ShouldReturnUpdatedProductWithId() {
        //Given
        Product product = new Product("1" , "Test" , "Test" , 5 , 4.500);
        ProductDto productDto = new ProductDto("Test", "Test", 10, 4.500);
        //When
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        //Method ausführen
        Product newProduct = service.updateProduct("1", productDto);
        //Ergebnisse prüfen
        assertThat(newProduct.stock()).isEqualTo(10);
        //Verify, dass die richtigen Methoden aufgerufen wurden
        verify(productRepository).findById("1");
        verify(productRepository).save(newProduct);
    }

    @Test
    public void deleteProductById_ShouldDeleteProduct() {
        String id = "12345";
        Product product = new Product(id, "name", "description", 10, 10.99);
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // WHEN
        service.deleteProductById(id);

        // THEN
        verify(productRepository).findById(id);
        verify(productRepository).deleteById(id);
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