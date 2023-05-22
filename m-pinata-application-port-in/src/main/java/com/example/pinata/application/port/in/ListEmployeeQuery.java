package com.example.pinata.application.port.in;

import com.example.pinata.domain.Employee;

import java.util.List;

public interface ListEmployeeQuery {

    List<Employee> getEmployeeListInfo();
}