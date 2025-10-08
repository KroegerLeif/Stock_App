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

    public Product findProduct(String search){
        try {
            return findProductById(search);
        } catch (ProductNotFoundException e) {
            try {
                return findProductByName(search);
            } catch (ProductNotFoundException e2) {
                String[] words = search.split(" ");
                for (String word : words) {
                    try {
                        return findProductByName(word);
                    } catch (ProductNotFoundException ignored) {
                        // Continue searching with next word
                    }
                }
                throw new ProductNotFoundException("Kein Product Gefunden");
            }
        }
    }
    
    public Product findProductByName(String name){
        return productRepository.findByName(name).orElseThrow(() -> new ProductNotFoundException("Kein Product Gefunden"));
    }
    
    public Product findProductById(String id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Kein Product Gefunden"));
    }

}
