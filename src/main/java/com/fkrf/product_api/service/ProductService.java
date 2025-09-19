package com.fkrf.product_api.service;

import com.fkrf.product_api.dto.ProductDTO;
import com.fkrf.product_api.model.Product;
import com.fkrf.product_api.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    private ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getQuantity()
        );
    }
    private Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        return product;
    }
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : products)
            productDTOS.add(toDTO(product));
        return productDTOS;
    }
    public Optional<ProductDTO> getProductById(long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if(!productOpt.isPresent())
            return Optional.empty();
        return Optional.of(toDTO(productOpt.get()));
    }
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product productCreated = productRepository.save(toEntity(productDTO));
        return toDTO(productCreated);
    }
    public Optional<ProductDTO> updateProduct(long id, ProductDTO productDTO) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (!productOpt.isPresent())
            return Optional.empty();
        Product product = productOpt.get();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        Product productUpdated = productRepository.save(product);
        return Optional.of(toDTO(productUpdated));
    }
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
