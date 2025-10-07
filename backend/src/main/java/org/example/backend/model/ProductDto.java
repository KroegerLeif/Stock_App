package org.example.backend.model;

import lombok.With;

@With
public record ProductDto(String name, String description, int stock, double price) {
    public Product toProduct(String id) {
        return new Product(id, name, description, stock, price);
    }
}
