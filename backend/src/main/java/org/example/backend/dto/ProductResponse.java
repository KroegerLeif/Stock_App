package org.example.backend.dto;

public record ProductResponse(String id,
                              String name,
                              String description,
                              double price) {
}
