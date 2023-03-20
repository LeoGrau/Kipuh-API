package com.nastypad.kipuhapi.kipuh.domain.service;


import com.nastypad.kipuhapi.kipuh.domain.model.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(Long id);
    Product create(Product saveProduct);
    Product update(Long id, Product updatedProduct);
    ResponseEntity<?> delete(Long id);
}
