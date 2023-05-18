package com.example.postgresql.service;

import com.example.port.out.ReadEmployeePort;
import com.example.port.out.SaveEmployeePort;
import com.example.postgresql.entity.Employee;
import com.example.postgresql.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeAdapter implements ReadEmployeePort, SaveEmployeePort {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> read(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> readList() {
        return employeeRepository.findAll();
    }
}