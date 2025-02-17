package com.alexa.store.service;

import com.alexa.store.dto.ProductDTO;
import com.alexa.store.entity.Product;
import com.alexa.store.exception.ProductAlreadyExistsException;
import com.alexa.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


        public Product addProduct(Product product) {

            Optional<Product> existingProduct = productRepository.findByName(product.getName());
            if (existingProduct.isPresent()) {
                throw new ProductAlreadyExistsException("Product with name '" + product.getName() + "' already exists.");
            }
            // Ensure productId is null so that Hibernate generates it
            product.setId(0);

            // Set createdAt automatically
            product.setCreatedAt(LocalDateTime.now());

            return productRepository.save(product);
        }

    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return convertToDTO(product);
    }

    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public void deleteProduct(int productId) {
        productRepository.deleteById(productId);
    }
}
