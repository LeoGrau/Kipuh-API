package com.nastypad.kipuhapi.kipuh.domain.repository;

import com.nastypad.kipuhapi.kipuh.domain.model.composite.ProductStoreKey;
import com.nastypad.kipuhapi.kipuh.domain.model.entity.ProductStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductStoreRepository extends JpaRepository<ProductStore, ProductStoreKey> {
    List<ProductStore> findAll();
    Optional<ProductStore> findById(ProductStoreKey id);
    boolean existsById(ProductStoreKey id);
}
