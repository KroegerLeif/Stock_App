package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.expetion.ProductNotFoundException;
import org.example.backend.model.Product;
import org.example.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ProductRepository productRepository;

    public Product findProductById(String id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Kein Product Gefunden"));
    }

}
