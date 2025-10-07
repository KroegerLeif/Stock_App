package org.example.backend.controller;

import lombok.AllArgsConstructor;
import org.example.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

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
