package com.nastypad.kipuhapi.kipuh.mapping.mappers;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.Product;
import com.nastypad.kipuhapi.kipuh.domain.model.entity.ProductStore;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateProductStoreResource;
import com.nastypad.kipuhapi.kipuh.resource.show.ProductStoreResource;
import com.nastypad.kipuhapi.kipuh.resource.update.UpdateProductStoreResource;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class ProductStoreMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ProductStoreResource toResource(ProductStore productStoreModel) {
        return mapper.map(productStoreModel, ProductStoreResource.class);
    }

    public List<ProductStoreResource> toListResource(List<ProductStore> productStores) {
        return mapper.mapList(productStores, ProductStoreResource.class);
    }

    public ProductStore createResourceToModel(CreateProductStoreResource createProductResource) {
        return mapper.map(createProductResource, ProductStore.class);
    }

    public ProductStore updateResourceToModel(UpdateProductStoreResource updateProductResource) {
        return mapper.map(updateProductResource, ProductStore.class);
    }

}
