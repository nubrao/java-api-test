package com.example.productapi.repository;

import com.example.productapi.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        try {
            ClassPathResource resource = new ClassPathResource("data/products.json");
            products = mapper.readValue(resource.getInputStream(),
                    new TypeReference<List<Product>>() {
                    });
        } catch (IOException e) {
            throw new RuntimeException("Failed to load products from JSON", e);
        }
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public List<Product> findByPriceRange(int initialRange, int finalRange) {
        return products.stream()
                .filter(p -> p.getPrice() >= initialRange && p.getPrice() <= finalRange)
                .collect(Collectors.toList());
    }
}