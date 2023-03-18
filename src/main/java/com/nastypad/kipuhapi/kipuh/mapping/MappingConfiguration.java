package com.nastypad.kipuhapi.kipuh.mapping;

import com.nastypad.kipuhapi.kipuh.mapping.mappers.ProductMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("kipuhMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public ProductMapper productMapper() {
        return new ProductMapper();
    }
}
