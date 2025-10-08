package org.example.backend.service;

import lombok.RequiredArgsConstructor;
import org.example.backend.dto.ProductResponse;
import org.example.backend.expetion.ProductNotFoundException;
import org.example.backend.model.Product;
import org.example.backend.model.ProductDto;
import org.example.backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Product updateProduct(String id, ProductDto productDto) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct = existingProduct .withName(productDto.name())
                            .withDescription(productDto.description())
                            .withStock(productDto.stock())
                            .withPrice(productDto.price());
                    return productRepository.save(existingProduct); })
                .orElse(null);
    }

    public boolean deleteProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Product findProductById(String id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Kein Product Gefunden"));
    }
}
