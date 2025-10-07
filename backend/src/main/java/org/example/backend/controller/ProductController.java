package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.example.backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
    }
}
