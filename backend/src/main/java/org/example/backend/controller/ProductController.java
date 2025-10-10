package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.dto.ProductResponse;
import org.example.backend.mapper.ProductMapper;
import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.service.ProductService;
import org.example.backend.service.SearchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final SearchService searchService;
    private final ProductMapper productMapper;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable String id) {
        boolean deletionSuccessful = productService.deleteProductById(id);

        if (deletionSuccessful){
            return new ResponseEntity<>("Product with id " + id + " was deleted", HttpStatus.OK);
        } else  {
            return new ResponseEntity<>("Product with id " + id + " was not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ProductResponse findProductById(@PathVariable String id) {
        return productMapper.mapProductToResponse(searchService.findProductById(id));
    }

    @GetMapping("/search/{search}")
    public List<ProductResponse> searchForProduct(@PathVariable String search) {
        return searchService.findProduct(search).stream()
                .map(productMapper::mapProductToResponse)
                .collect(Collectors.toList());
    }

}
