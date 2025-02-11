package com.learner.department.bootstrap;

import com.learner.department.dto.DepartmentDto;
import com.learner.department.service.DepartmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BootstrapData implements CommandLineRunner {

    DepartmentService departmentService;

    public BootstrapData(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            departmentDtos.add(
                    new DepartmentDto(
                            "Department"+i,
                            "DepartmentDesc"+i,
                            "DepartmentCode"+i));
        }

        departmentService.insertAllDepartment(departmentDtos);
    }
}
