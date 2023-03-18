package com.nastypad.kipuhapi.shared.mapping;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {
    @Bean
    public EnhancedModelMapper enhancedModelMapper() {
        return new EnhancedModelMapper();
    }
}
