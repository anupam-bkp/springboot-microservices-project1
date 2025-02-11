package com.learner.department.service;

import com.learner.department.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {

    //To Bootstrap Data
    void insertAllDepartment(List<DepartmentDto> departmentDtos);

    DepartmentDto save(DepartmentDto departmentDto);

    DepartmentDto findDepartmentByCode(String departmentCode);

}
