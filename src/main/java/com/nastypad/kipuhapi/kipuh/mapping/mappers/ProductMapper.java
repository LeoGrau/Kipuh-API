package com.nastypad.kipuhapi.kipuh.mapping.mappers;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.Product;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateProductResource;
import com.nastypad.kipuhapi.kipuh.resource.show.ProductResource;
import com.nastypad.kipuhapi.kipuh.resource.update.UpdateProductResource;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ProductMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ProductResource toResource(Product productModel) {
        return mapper.map(productModel, ProductResource.class);
    }

    public List<ProductResource> toListResource(List<Product> products) {
        return mapper.mapList(products, ProductResource.class);
    }

    public Product toModel(ProductResource productResource) {
        return mapper.map(productResource, Product.class);
    }

    public Product createResourceToModel(CreateProductResource createProductResource) {
        return mapper.map(createProductResource, Product.class);
    }

    public Product updateResourceToModel(UpdateProductResource updateProductResource) {
        return mapper.map(updateProductResource, Product.class);
    }
}
