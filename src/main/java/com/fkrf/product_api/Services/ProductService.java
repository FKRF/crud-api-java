package com.fkrf.product_api.Services;

import com.fkrf.product_api.Models.Product;
import com.fkrf.product_api.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(long id, Product newProduct) {
        var productOpt = productRepository.findById(id);
        if (!productOpt.isPresent())
            throw  new RuntimeException("Product does not exists");
        Product product = productOpt.get();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setQuantity(newProduct.getQuantity());
        return productRepository.save(product);
    }
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
