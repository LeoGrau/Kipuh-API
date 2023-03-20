package com.nastypad.kipuhapi.kipuh.domain.service;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.Store;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StoreService {
    List<Store> getAll();
    Store getById(Long storeId);
    Store create(Store newStore);
    Store update(Long storeId, Store updatedStore);
    ResponseEntity<?> delete(Long storeId);
}
