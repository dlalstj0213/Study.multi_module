package com.example.pinata.adapter.in.web;

import com.example.pinata.application.port.in.ListEmployeeQuery;
import com.example.pinata.application.port.in.LoadEmployeeQuery;
import com.example.pinata.domain.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/pinata/employee")
@RestController
public class InquiryEmployeeController {

    private final LoadEmployeeQuery loadEmployeeQuery;
    private final ListEmployeeQuery listEmployeeQuery;

    @GetMapping("")
    private ResponseEntity<List<EmployeeResponse>> getEmployeeList() {
        List<Employee> list =  listEmployeeQuery.getEmployeeListInfo();
        return ResponseEntity.ok(list.stream().map(item -> new EmployeeResponse(item.getEmail(), item.getName(), item.getSex())).toList());
    }

    @GetMapping("{id}")
    private ResponseEntity<EmployeeResponse> getEmployee(@PathVariable String id) {
        Employee employee = loadEmployeeQuery.getEmployeeInfo(Long.parseLong(id));
        return ResponseEntity.ok(new EmployeeResponse(employee.getEmail(), employee.getName(), employee.getSex()));
    }
}