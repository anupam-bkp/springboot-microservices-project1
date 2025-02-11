package com.learner.department.mapper;

import com.learner.department.dto.DepartmentDto;
import com.learner.department.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper{

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDto mapToDepartmentDto(Department department);

    Department mapToDepartment(DepartmentDto departmentDto);

}
