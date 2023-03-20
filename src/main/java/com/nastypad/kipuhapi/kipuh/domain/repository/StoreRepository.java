package com.nastypad.kipuhapi.kipuh.domain.repository;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAll();
    Optional<Store> findByStoreId(Long storeId);
    Boolean existsByStoreId(Long storeId);
}
