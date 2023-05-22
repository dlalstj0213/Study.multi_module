package com.example.pinata.application.port.in;

import com.example.pinata.domain.Employee;

public interface LoadEmployeeQuery {
    Employee getEmployeeInfo(Long id);
}