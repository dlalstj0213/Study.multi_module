package com.example.pinata.application.service;


import com.example.pinata.application.port.in.ListEmployeeQuery;
import com.example.pinata.application.port.in.LoadEmployeeQuery;
import com.example.pinata.application.port.in.RegisterEmployeeQuery;
import com.example.pinata.application.port.out.CreateEmployeeInfo;
import com.example.pinata.application.port.out.ReadEmployeeInfo;
import com.example.pinata.application.port.out.ReadEmployeeList;
import com.example.pinata.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService implements LoadEmployeeQuery, ListEmployeeQuery, RegisterEmployeeQuery {

    private final ReadEmployeeList readEmployeeList;
    private final ReadEmployeeInfo readEmployeeInfo;
    private final CreateEmployeeInfo createEmployeeInfo;


    @Override
    public List<Employee> getEmployeeListInfo() {
        return readEmployeeList.readList();
    }

    @Override
    public Employee getEmployeeInfo(Long id) {
        return readEmployeeInfo.readInfo(id);
    }

    @Override
    public Employee registerEmployee(Employee employee) {
        return createEmployeeInfo.createInfo(employee);
    }
}