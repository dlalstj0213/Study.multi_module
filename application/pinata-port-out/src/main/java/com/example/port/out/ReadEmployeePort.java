package com.example.port.out;

import com.example.postgresql.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface ReadEmployeePort {

    Optional<Employee> read(Long id);

    List<Employee> readList();
}