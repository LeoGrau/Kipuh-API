package com.nastypad.kipuhapi.kipuh.resource.create;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nastypad.kipuhapi.kipuh.domain.model.composite.ProductStoreKey;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductStoreResource {
    private long productId;
    private long storeId;
    private float price;

    @JsonIgnore
    public ProductStoreKey getProductStoreKeyId() {
        return new ProductStoreKey(productId, storeId);
    }
}
