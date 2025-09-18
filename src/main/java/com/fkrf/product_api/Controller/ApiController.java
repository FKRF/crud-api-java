package com.fkrf.product_api.Controller;

import com.fkrf.product_api.Services.ProductService;
import com.fkrf.product_api.Models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ApiController {
    private final ProductService productService;
    public ApiController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable     Long id) {
        var product = productService.getProductById(id);
        if (!product.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(product.get());
    }
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product productCreated = productService.saveProduct(product);
        return ResponseEntity.ok(productCreated);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product newProduct) {
        var product = productService.getProductById(id);
        if (!product.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productService.updateProduct(id, newProduct));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
