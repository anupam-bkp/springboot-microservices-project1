package com.learner.department.service.impl;

import com.learner.department.dto.DepartmentDto;
import com.learner.department.entity.Department;
import com.learner.department.mapper.DepartmentMapper;
import com.learner.department.repository.DepartmentRepository;
import com.learner.department.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void insertAllDepartment(List<DepartmentDto> departmentDtos) {

        List<Department> list = departmentDtos.stream()
                .map(DepartmentMapper.INSTANCE::mapToDepartment)
                .toList();

        departmentRepository.saveAll(list);
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        Department savedDepartment = departmentRepository.save(DepartmentMapper.INSTANCE.mapToDepartment(departmentDto));
        return DepartmentMapper.INSTANCE.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto findDepartmentByCode(String departmentCode) {
        Department byDepartmentCode = departmentRepository.findByDepartmentCode(departmentCode);
        return DepartmentMapper.INSTANCE.mapToDepartmentDto(byDepartmentCode);
    }

}
