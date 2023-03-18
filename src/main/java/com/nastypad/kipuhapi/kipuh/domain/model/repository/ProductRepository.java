package com.nastypad.kipuhapi.kipuh.domain.model.repository;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Boolean existsById(long id);
}
