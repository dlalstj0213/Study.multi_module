package com.example.pinata.application.port.in;

import com.example.pinata.domain.Employee;

public interface RegisterEmployeeQuery {

    Employee registerEmployee(Employee employee);
}