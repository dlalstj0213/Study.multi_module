package com.example.port.in.query;

import com.example.postgresql.entity.Employee;

import java.util.List;

public interface GetEmployeeQuery {

    Employee getEmployee(Long id);

    List<Employee> getEmployees();
}