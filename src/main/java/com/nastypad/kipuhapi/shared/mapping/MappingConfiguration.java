package com.nastypad.kipuhapi.shared.mapping;

import com.nastypad.kipuhapi.kipuh.domain.model.entity.ProductStore;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateProductResource;
import com.nastypad.kipuhapi.kipuh.resource.create.CreateProductStoreResource;
import com.nastypad.kipuhapi.kipuh.resource.show.ProductStoreResource;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper enhancedModelMapper() {
        EnhancedModelMapper mapper = new EnhancedModelMapper();

        //ProductStore to ProductStoreResource
        mapper.typeMap(ProductStore.class, ProductStoreResource.class).addMappings(
                _mapper -> {
                    _mapper.map(source -> source.getProduct().getProductName(), ProductStoreResource::setProductName);
                    _mapper.map(source -> source.getStore().getStoreName(), ProductStoreResource::setStoreName);
                }
        );

        mapper.typeMap(CreateProductStoreResource.class, ProductStore.class).addMappings(
                _mapper -> {
                    _mapper.map(createProductStoreResource -> createProductStoreResource.getProductStoreKeyId(), ProductStore::setId);
                }
        );

        return mapper;
    }
}
