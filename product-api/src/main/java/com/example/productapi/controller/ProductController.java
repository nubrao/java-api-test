package com.example.productapi.controller;

import com.example.productapi.model.Product;
import com.example.productapi.service.ProductService;
import com.example.productapi.exception.InvalidPriceRangeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{initial_range}/{final_range}")
    public ResponseEntity<?> getProductsByPriceRange(
            @PathVariable int initial_range,
            @PathVariable int final_range) {
        
        // Validate negative values
        if (initial_range < 0 || final_range < 0) {
            throw new InvalidPriceRangeException("Price range cannot contain negative values");
        }

        // Validate inverted range
        if (initial_range > final_range) {
            throw new InvalidPriceRangeException("Initial price cannot be greater than final price");
        }

        return ResponseEntity.ok(productService.findByPriceRange(initial_range, final_range));
    }

    @GetMapping
    public ResponseEntity<List<String>> getAllProductsSortedByPrice() {
        return ResponseEntity.ok(productService.findAllSortedByPrice());
    }
}