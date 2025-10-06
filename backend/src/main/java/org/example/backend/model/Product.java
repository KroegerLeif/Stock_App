package org.example.backend.model;

import lombok.With;

@With
public record Product(String id, String name, String description, int stock, double price) {
}
