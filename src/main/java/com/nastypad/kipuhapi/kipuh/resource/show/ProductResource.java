package com.nastypad.kipuhapi.kipuh.resource.show;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ProductResource {
    private Long productId;
    private String productName;
    private float price;
}
