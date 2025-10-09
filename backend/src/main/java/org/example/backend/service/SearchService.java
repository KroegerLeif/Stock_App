package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.expetion.ProductNotFoundException;
import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ProductRepository productRepository;

    public List<Product> findProduct(String search){
        return productRepository.findByNameContainingIgnoreCase(search);
    }

    public Product findProductByName(String name){
        return productRepository.findByName(name).orElseThrow(() -> new ProductNotFoundException("Kein Product Gefunden"));
    }

    public Product findProductById(String id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Kein Product Gefunden"));
    }

}
