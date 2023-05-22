package com.example.pinata.application.port.out;

import com.example.pinata.domain.Employee;

import java.util.List;

public interface ReadEmployeeList {
    List<Employee> readList();
}