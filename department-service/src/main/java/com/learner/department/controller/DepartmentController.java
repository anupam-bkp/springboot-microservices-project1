package com.learner.department.controller;

import com.learner.department.dto.DepartmentDto;
import com.learner.department.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    //Save a new Department
    //http://locahost:8080/api/departments
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto savedDepartmentDto = departmentService.save(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }

    //Get Department by code
    //http://localhost:8080/api/departments/department-code
    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentByCode = departmentService.findDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentByCode, HttpStatus.OK);
    }

}
