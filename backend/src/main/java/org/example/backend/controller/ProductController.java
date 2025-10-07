package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.dto.ProductResponse;
import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.service.ProductService;
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

    @GetMapping("/{id}")
    public ProductResponse findProductById(@PathVariable String id ){
        return mapProductToResponse(
                productService.findProductById(id)
        );
    }

    private ProductResponse mapProductToResponse(Product product){
        return new ProductResponse(
                product.name(),
                product.description(),
                product.price()
        );
    }
}
