package com.nastypad.kipuhapi.kipuh.service;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.Product;
import com.nastypad.kipuhapi.kipuh.domain.repository.ProductRepository;
import com.nastypad.kipuhapi.kipuh.domain.service.ProductService;
import com.nastypad.kipuhapi.shared.exception.ResourceNotFoundException;
import com.nastypad.kipuhapi.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String entity = "Product";
    private final ProductRepository productRepository;
    private final Validator validator;

    public ProductServiceImpl(ProductRepository productRepository, Validator validator) {
        this.productRepository = productRepository;
        this.validator = validator;
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(Long productId) {
        return productRepository.findByProductId(productId).orElseThrow(() -> new ResourceNotFoundException(entity, productId));
    }

    @Override
    public Product create(Product product) {
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        if(!violations.isEmpty())
            throw new ResourceValidationException(entity, violations);
        return productRepository.save(product);
    }

    @Override
    public Product update(Long productId, Product updatedProduct) {
        Set<ConstraintViolation<Product>> violations = validator.validate(updatedProduct);
        if(!violations.isEmpty())
            throw new ResourceValidationException(entity, violations);
        if(!productRepository.existsByProductId(productId))
            throw new ResourceValidationException("Product does not exist.");

        Product existingProduct = productRepository.findByProductId(productId).get();
        existingProduct.setProductName(updatedProduct.getProductName());
        return productRepository.save(existingProduct);
    }

    @Override
    public ResponseEntity<?> delete(Long productId) {
        return productRepository.findByProductId(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(entity, productId));
    }
}
