package com.example.web.online;

import com.example.port.in.query.GetEmployeeQuery;
import com.example.port.in.query.SetEmployeeQuery;
import com.example.postgresql.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/online/employee")
@RestController
public class EmployeeController {

    private final GetEmployeeQuery getEmployeeQuery;
    private final SetEmployeeQuery setEmployeeQuery;

    @GetMapping("")
    private ResponseEntity<List<Employee>> getEmployee() {
        return ResponseEntity.ok(getEmployeeQuery.getEmployees());
    }

    @GetMapping("{id}")
    private ResponseEntity<Employee> getEmployee(@PathVariable String id) {
        return ResponseEntity.ok(getEmployeeQuery.getEmployee(Long.parseLong(id)));
    }

    @PostMapping("")
    private ResponseEntity<Employee> postEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(setEmployeeQuery.setEmployee(employee));
    }
}