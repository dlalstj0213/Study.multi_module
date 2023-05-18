package com.example.service;

import com.example.port.in.query.GetEmployeeQuery;
import com.example.port.out.ReadEmployeePort;
import com.example.postgresql.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetEmployeeService implements GetEmployeeQuery {

    private final ReadEmployeePort readEmployeePort;

    @Override
    public Employee getEmployee(Long id) {
        return readEmployeePort.read(id).orElse(null);
    }

    @Override
    public List<Employee> getEmployees() {
        return readEmployeePort.readList();
    }
}