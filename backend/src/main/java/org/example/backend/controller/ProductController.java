package org.example.backend.controller;

import org.example.backend.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    public List<Product> gelAll() {
        return null;
    }
}
