package org.example.backend.mapper;

import org.example.backend.dto.ProductResponse;
import org.example.backend.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public ProductResponse mapProductToResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.name(),
                product.description(),
                product.price()
        );
    }
}