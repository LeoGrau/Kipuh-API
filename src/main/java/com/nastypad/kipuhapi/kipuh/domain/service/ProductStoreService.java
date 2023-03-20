package com.nastypad.kipuhapi.kipuh.domain.service;

import com.nastypad.kipuhapi.kipuh.domain.model.composite.ProductStoreKey;
import com.nastypad.kipuhapi.kipuh.domain.model.entity.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductStoreService {
    List<Store> getAll();
    Store getById(ProductStoreKey id);
    Store create(Store newStore);
    Store update(ProductStoreKey id, Store updatedStore);
    ResponseEntity<?> delete(ProductStoreKey id);
}
