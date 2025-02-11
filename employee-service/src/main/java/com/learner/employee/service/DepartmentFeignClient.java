package com.learner.employee.service;

import com.learner.employee.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/api/departments", name = "Department-Feign-Client")
public interface DepartmentFeignClient {

    @GetMapping("{department-code}")
    DepartmentDto getDepartmentByCode(@PathVariable("department-code") String departmentCode);

}
