package com.example.service;

import com.example.port.in.query.SetEmployeeQuery;
import com.example.port.out.SaveEmployeePort;
import com.example.postgresql.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SetEmployeeService implements SetEmployeeQuery {

    private final SaveEmployeePort saveEmployeePort;

    @Override
    public Employee setEmployee(Employee employee) {
        return saveEmployeePort.save(employee);
    }
}