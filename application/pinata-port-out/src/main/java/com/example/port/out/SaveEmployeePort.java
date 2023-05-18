package com.example.port.out;

import com.example.postgresql.entity.Employee;

public interface SaveEmployeePort {

    Employee save(Employee employee);
}