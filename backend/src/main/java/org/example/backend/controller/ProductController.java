package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
