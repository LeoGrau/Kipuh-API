package com.nastypad.kipuhapi.kipuh.service;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.Product;
import com.nastypad.kipuhapi.kipuh.domain.model.entity.Store;
import com.nastypad.kipuhapi.kipuh.domain.repository.StoreRepository;
import com.nastypad.kipuhapi.kipuh.domain.service.StoreService;
import com.nastypad.kipuhapi.shared.exception.ResourceNotFoundException;
import com.nastypad.kipuhapi.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StoreServiceImpl implements StoreService {

    private static final String entity = "Store";
    private final StoreRepository storeRepository;
    private final Validator validator;

    public StoreServiceImpl(StoreRepository storeRepository, Validator validator) {
        this.storeRepository = storeRepository;
        this.validator = validator;
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store getById(Long storeId) {
        return storeRepository.findByStoreId(storeId).orElseThrow(() -> new ResourceNotFoundException(entity, storeId));
    }

    @Override
    public Store create(Store newStore) {
        Set<ConstraintViolation<Store>> violations = validator.validate(newStore);
        if(!violations.isEmpty())
            throw new ResourceValidationException(entity, violations);
        return storeRepository.save(newStore);
    }

    @Override
    public Store update(Long storeId, Store updatedStore) {
        Set<ConstraintViolation<Store>> violations = validator.validate(updatedStore);
        if(!violations.isEmpty())
            throw new ResourceValidationException(entity, violations);
        if(!storeRepository.existsByStoreId(storeId))
            throw new ResourceValidationException("Store does not exist.");

        Store existingStore = storeRepository.findByStoreId(storeId).get();
        existingStore.setStoreName(updatedStore.getStoreName());
        return storeRepository.save(existingStore);
    }

    @Override
    public ResponseEntity<?> delete(Long storeId) {
        return storeRepository.findByStoreId(storeId).map(store -> {
            storeRepository.delete(store);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(entity, storeId));
    }
}
