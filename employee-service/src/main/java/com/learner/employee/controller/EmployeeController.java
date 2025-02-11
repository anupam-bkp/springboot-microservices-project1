package com.learner.employee.controller;

import com.learner.employee.dto.ApiResponseDto;
import com.learner.employee.dto.EmployeeDto;
import com.learner.employee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //Save an Employee
    //http://localhost:8081/api/employees
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployeeDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployeeDto, HttpStatus.CREATED);
    }

    //Get an Employee and corresponding Department details by id
    //http://localhost:8081/api/employees/id
    @GetMapping("{id}")
    public ResponseEntity<ApiResponseDto> getEmployeeById(@PathVariable("id") Long id){
        ApiResponseDto apiResponseById = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(apiResponseById, HttpStatus.OK);
    }

}
