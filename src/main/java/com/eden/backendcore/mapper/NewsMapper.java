package com.eden.backendcore.mapper;

import com.eden.backendcore.model.News;
import com.eden.backendcore.viewmodel.NewsVM;
import com.eden.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsMapper extends BaseMapper<NewsVM, News> {
}
