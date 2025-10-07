package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody ProductDto productDto) {
        Product updatedProduct = productService.updateProduct(id, productDto);
        if (updatedProduct != null) {
            return updatedProduct;
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Produkt mit ID " + id + " nicht gefunden"
            );
        }
    }
}
