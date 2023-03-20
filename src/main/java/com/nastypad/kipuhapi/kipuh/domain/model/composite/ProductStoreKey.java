package com.nastypad.kipuhapi.kipuh.domain.model.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProductStoreKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "storeId")
    private long storeId;
}
