package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final IdService idService;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product add(ProductDto productDto) {
        Product product = productDto.toProduct(idService.randomId());
        productRepository.save(product);
        return product;
    }

    public boolean deleteProductById(String id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
