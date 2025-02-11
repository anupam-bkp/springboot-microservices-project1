package com.learner.employee.service;

import com.learner.employee.dto.ApiResponseDto;
import com.learner.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    void insertEmployees(List<EmployeeDto> employeeDtos);

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    ApiResponseDto getEmployeeById(Long id);
}
