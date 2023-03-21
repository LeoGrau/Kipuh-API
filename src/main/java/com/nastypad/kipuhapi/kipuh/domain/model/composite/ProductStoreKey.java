package com.nastypad.kipuhapi.kipuh.domain.model.composite;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Embeddable
public class ProductStoreKey implements Serializable {
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "storeId")
    private long storeId;

    protected ProductStoreKey() {}

    public ProductStoreKey(Long productId, Long storeId) {
        this.productId = productId;
        this.storeId = storeId;
    }
}
