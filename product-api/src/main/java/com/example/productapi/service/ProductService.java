package com.example.productapi.service;

import com.example.productapi.exception.InvalidPriceRangeException;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findByPriceRange(int initialRange, int finalRange) {
        // Negative values validation
        if (initialRange < 0 || finalRange < 0) {
            throw new InvalidPriceRangeException("Price range cannot contain negative values");
        }

        // Inverted range validation
        if (initialRange > finalRange) {
            throw new InvalidPriceRangeException("Initial price cannot be greater than final price");
        }

        List<Product> products = productRepository.findByPriceRange(initialRange, finalRange);

        // Empty result validation
        if (products.isEmpty()) {
            throw new InvalidPriceRangeException(
                    String.format("No products found in the price range %d to %d", initialRange, finalRange));
        }

        return products;
    }

    public List<String> findAllSortedByPrice() {
        return productRepository.findAll().stream()
                .sorted((p1, p2) -> p1.getPrice().compareTo(p2.getPrice()))
                .map(Product::getItem)
                .collect(Collectors.toList());
    }
}