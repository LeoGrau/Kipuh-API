package com.nastypad.kipuhapi.kipuh.mapping.mappers;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.Product;
import com.nastypad.kipuhapi.kipuh.domain.model.entity.Store;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateProductResource;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateStoreResource;
import com.nastypad.kipuhapi.kipuh.resource.show.ProductResource;
import com.nastypad.kipuhapi.kipuh.resource.show.StoreResource;
import com.nastypad.kipuhapi.kipuh.resource.update.UpdateProductResource;
import com.nastypad.kipuhapi.kipuh.resource.update.UpdateStoreResource;
import com.nastypad.kipuhapi.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

public class StoreMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public StoreResource toResource(Store storeModel) {
        return mapper.map(storeModel, StoreResource.class);
    }

    public List<StoreResource> toListResource(List<Store> stores) {
        return mapper.mapList(stores, StoreResource.class);
    }

    public Store createResourceToModel(CreateStoreResource createStoreResource) {
        return mapper.map(createStoreResource, Store.class);
    }

    public Store updateResourceToModel(UpdateStoreResource updateStoreResource) {
        return mapper.map(updateStoreResource, Store.class);
    }

    public Store toModel(StoreResource storeResource) {
        return mapper.map(storeResource, Store.class);
    }

}
