package com.nastypad.kipuhapi.shared.mapping;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class EnhancedModelMapper extends ModelMapper {
    public EnhancedModelMapper() {
        super();
    }

    public <S,T> List<T> mapList(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream().map((sourceItem) -> this.map(sourceItem, targetClass)).collect(Collectors.toList());
    }
}
