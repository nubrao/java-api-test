package com.example.productapi.repository;

import com.example.productapi.model.Product;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> findAll();

    List<Product> findByPriceRange(int initialRange, int finalRange);
}