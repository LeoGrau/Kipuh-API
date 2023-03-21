package com.nastypad.kipuhapi.kipuh.service;

import com.nastypad.kipuhapi.kipuh.domain.model.composite.ProductStoreKey;
import com.nastypad.kipuhapi.kipuh.domain.model.entity.ProductStore;
import com.nastypad.kipuhapi.kipuh.domain.repository.ProductStoreRepository;
import com.nastypad.kipuhapi.kipuh.domain.service.ProductStoreService;
import com.nastypad.kipuhapi.shared.exception.ResourceNotFoundException;
import com.nastypad.kipuhapi.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProductStoreServiceImpl implements ProductStoreService {

    private static final String entity = "ProductStore";
    private final ProductStoreRepository productStoreRepository;
    private final Validator validator;

    public ProductStoreServiceImpl(ProductStoreRepository productStoreRepository, Validator validator) {
        this.productStoreRepository = productStoreRepository;
        this.validator = validator;
    }

    @Override
    public List<ProductStore> getAll() {
        return productStoreRepository.findAll();
    }

    @Override
    public ProductStore getById(ProductStoreKey id) {
        return productStoreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("%s with composite key %d %d does not exist", entity, id.getProductId(), id.getStoreId())));
    }

    @Override
    public ProductStore create(ProductStore newStore) {
        Set<ConstraintViolation<ProductStore>> violations = validator.validate(newStore);
        if(!violations.isEmpty())
            throw new ResourceValidationException(entity, violations);
        return productStoreRepository.save(newStore);
    }

    @Override
    public ProductStore update(ProductStoreKey id, ProductStore updatedStore) {
        Set<ConstraintViolation<ProductStore>> violations = validator.validate(updatedStore);
        if(!violations.isEmpty())
            throw new ResourceValidationException(entity, violations);
        if(!productStoreRepository.existsById(id))
            throw new ResourceValidationException("ProductStore does not exist.");

        ProductStore existingProductStore = productStoreRepository.findById(id).get();
        existingProductStore.setPrice(updatedStore.getPrice());
        return productStoreRepository.save(existingProductStore);
    }

    @Override
    public ResponseEntity<?> delete(ProductStoreKey id) {
        return productStoreRepository.findById(id).map(productStore -> {
            productStoreRepository.delete(productStore);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ProductStore does not exist."));
    }
}
