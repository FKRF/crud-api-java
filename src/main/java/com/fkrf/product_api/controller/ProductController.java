package com.fkrf.product_api.controller;

import com.fkrf.product_api.dto.ProductDTO;
import com.fkrf.product_api.service.ProductService;
import com.fkrf.product_api.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> productDTOs = productService.getAllProducts();
        return ResponseEntity.ok(productDTOs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable     Long id) {
        Optional<ProductDTO> productOpt = productService.getProductById(id);
        return productOpt.isPresent() ? ResponseEntity.ok(productOpt.get()) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO productCreated = productService.createProduct(productDTO);
        return ResponseEntity.ok(productCreated);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO newProduct) {
        Optional<ProductDTO> productOpt = productService.updateProduct(id, newProduct);
        return productOpt.isPresent() ? ResponseEntity.ok(productOpt.get()) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
