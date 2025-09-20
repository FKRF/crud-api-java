package com.fkrf.product_api.controller;

import com.fkrf.product_api.dto.ProductDTO;
import com.fkrf.product_api.service.ProductService;
import com.fkrf.product_api.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        ProductDTO productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }
    @PostMapping
    @PreAuthorize("hasROLE('ADMIN')")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO productCreated = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasROLE('ADMIN')")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO productUpdated = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(productUpdated);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasROLE('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
