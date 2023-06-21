package com.eden.backendcore.mapper;

import com.eden.backendcore.model.Project;
import com.eden.backendcore.viewmodel.ProjectVM;
import com.eden.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProjectMapper extends BaseMapper<ProjectVM, Project> {
}
