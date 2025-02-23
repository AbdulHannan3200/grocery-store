package com.alexa.store.controller;

import com.alexa.store.dto.ProductDTO;
import com.alexa.store.entity.Product;
import com.alexa.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/product_list")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ProductDTO getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable int productId) {
        productService.deleteProduct(productId);
    }
}
