package com.example.pinata.application.port.out;

import com.example.pinata.domain.Employee;

public interface ReadEmployeeInfo {

    Employee readInfo(Long id);
}