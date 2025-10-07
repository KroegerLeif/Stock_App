package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.backend.model.ProductDto;
import org.example.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String id) {
        boolean deletionSuccessful = productService.deleteProductById(id);

        if (deletionSuccessful){
            return new ResponseEntity<>("Product with id " + id + " was deleted", HttpStatus.OK);
        } else  {
            return new ResponseEntity<>("Product with id " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }
}
