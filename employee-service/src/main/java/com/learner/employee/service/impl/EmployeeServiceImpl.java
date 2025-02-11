package com.learner.employee.service.impl;

import com.learner.employee.dto.ApiResponseDto;
import com.learner.employee.dto.DepartmentDto;
import com.learner.employee.dto.EmployeeDto;
import com.learner.employee.entity.Employee;
import com.learner.employee.mapper.EmployeeMapper;
import com.learner.employee.repository.EmployeeRepository;
import com.learner.employee.service.DepartmentFeignClient;
import com.learner.employee.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
//    RestTemplate restTemplate;
//    WebClient webclient;
    DepartmentFeignClient departmentFeignClient;

    //RestTemplate
  /*  public EmployeeServiceImpl(EmployeeRepository employeeRepository, RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
    }*/

    //WebClient
    /*public EmployeeServiceImpl(EmployeeRepository employeeRepository, WebClient webclient) {
        this.employeeRepository = employeeRepository;
        this.webclient = webclient;
    }*/

    //OpenFeign
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentFeignClient departmentFeignClient) {
        this.employeeRepository = employeeRepository;
        this.departmentFeignClient = departmentFeignClient;
    }

    @Override
    public void insertEmployees(List<EmployeeDto> employeeDtos) {

        List<Employee> employees = employeeDtos.stream().map(EmployeeMapper.INSTANCE::mapToEmployee).toList();
        employeeRepository.saveAll(employees);
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto){
        Employee savedEmployee = employeeRepository.save(EmployeeMapper.INSTANCE.mapToEmployee(employeeDto));
        return EmployeeMapper.INSTANCE.mapToEmployeeDto(savedEmployee);
    }

    public ApiResponseDto getEmployeeById(Long id){

        Optional<Employee> employeeByIdOpt = employeeRepository.findById(id);

        if(employeeByIdOpt.isPresent()) {
            EmployeeDto employeeDto = EmployeeMapper.INSTANCE.mapToEmployeeDto(employeeByIdOpt.get());

            //Fetch DepartmentDto

            //1. RestClient
            /*ResponseEntity<DepartmentDto> responseEntity =
                    restTemplate.getForEntity("http://localhost:8080/api/departments/" +
                                    employeeDto.getDepartmentCode(), DepartmentDto.class);

            DepartmentDto departmentDto = responseEntity.getBody();*/

            //2. WebClient
            /*DepartmentDto departmentDto = webclient.get()
                    .uri("http://localhost:8080/api/departments/" + employeeDto.getDepartmentCode())
                    .retrieve()
                    .bodyToMono(DepartmentDto.class)
                    .block();*/

            //3. OpenFeign
            final DepartmentDto departmentByCode =
                    departmentFeignClient.getDepartmentByCode(employeeDto.getDepartmentCode());


            return new ApiResponseDto(employeeDto, departmentByCode);
        }

        return null;
    }

}
