package com.nastypad.kipuhapi.kipuh.domain.service;

import com.nastypad.kipuhapi.kipuh.domain.model.composite.ProductStoreKey;
import com.nastypad.kipuhapi.kipuh.domain.model.entity.ProductStore;
import com.nastypad.kipuhapi.kipuh.domain.model.entity.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductStoreService {
    List<ProductStore> getAll();
    ProductStore getById(ProductStoreKey id);
    ProductStore create(ProductStore newStore);
    ProductStore update(ProductStoreKey id, ProductStore updatedStore);
    ResponseEntity<?> delete(ProductStoreKey id);
}
