package com.nastypad.kipuhapi.kipuh.domain.model.entity;

import com.nastypad.kipuhapi.kipuh.domain.model.composite.ProductStoreKey;
import jakarta.persistence.*;
import lombok.*;
import org.springdoc.core.converters.models.MonetaryAmount;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_stores")
public class ProductStore {
    @EmbeddedId
    private ProductStoreKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("storeId")
    @JoinColumn(name = "store_id")
    private Store store;

    private float price;
}
