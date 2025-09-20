package com.fkrf.product_api.service;

import com.fkrf.product_api.dto.ProductDTO;
import com.fkrf.product_api.model.Product;
import com.fkrf.product_api.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public ProductDTO getProductById(long id) {
        Product product = productRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return toDTO(product);
    }
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productDTO.getName().isEmpty() || productDTO.getName().isBlank())
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type a valid name");
        boolean productNameExists = (productRepository.findByName(productDTO.getName())).isPresent();
        if (productNameExists)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already a product with this name");
        Product productCreated = productRepository.save(toEntity(productDTO));
        return toDTO(productCreated);
    }
    public ProductDTO updateProduct(long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found") );
        if (productDTO.getName().isEmpty() || productDTO.getName().isBlank())
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type a valid name");
        Optional<Product> productExisting = productRepository.findByName(productDTO.getName());
        if (productExisting.isPresent() && productExisting.get().getId() != id)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already a product with this name");

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setQuantity(productDTO.getQuantity());
        Product productUpdated = productRepository.save(product);

        return toDTO(productUpdated);
    }
    public void deleteProduct(long id) {
        if (!productRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        productRepository.deleteById(id);
    }
}
