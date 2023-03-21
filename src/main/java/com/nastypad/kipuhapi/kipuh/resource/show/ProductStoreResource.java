package com.nastypad.kipuhapi.kipuh.resource.show;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProductStoreResource {
    private String productName;
    private String storeName;
    private float price;

}
