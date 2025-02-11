package com.learner.employee.bootstrap;

import com.learner.employee.dto.EmployeeDto;
import com.learner.employee.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    EmployeeService employeeService;

    public BootstrapData(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (int i = 1; i < 5; i++) {

            employeeDtos.add(new EmployeeDto("Firstname"+i, "LastName"+i, "Email"+i,
                    "DepartmentCode"+i));
        }

        employeeService.insertEmployees(employeeDtos);
    }
}
