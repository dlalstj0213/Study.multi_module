package com.example.pinata.adapter.in.web;


import com.example.pinata.application.port.in.RegisterEmployeeQuery;
import com.example.pinata.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pinata/employee")
public class RegisterEmployeeController {

    private final RegisterEmployeeQuery registerEmployeeQuery;

    @PostMapping("")
    private ResponseEntity<EmployeeResponse> postEmployee(@RequestBody EmployeeRequest employee) {
        Employee newEmployee = registerEmployeeQuery.registerEmployee(new Employee(null, employee.email(), employee.name(), employee.sex()));
        return ResponseEntity.ok(new EmployeeResponse(newEmployee.getEmail(), newEmployee.getName(), newEmployee.getSex()));
    }
}