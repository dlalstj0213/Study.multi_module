package com.example.pinata.adapter.out.persistence;

import com.example.pinata.adapter.out.entity.EmployeeEntity;
import com.example.pinata.adapter.out.entity.EmployeeRepository;
import com.example.pinata.application.port.out.CreateEmployeeInfo;
import com.example.pinata.application.port.out.ReadEmployeeInfo;
import com.example.pinata.application.port.out.ReadEmployeeList;
import com.example.pinata.domain.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class EmployeeAdapter implements ReadEmployeeInfo, ReadEmployeeList, CreateEmployeeInfo {

    private final EmployeeRepository repository;

    @Override
    public Employee createInfo(Employee employee) {
        EmployeeEntity saved = repository.save(EmployeeEntity.builder()
                                                             .email(employee.getEmail())
                                                             .sex(employee.getSex())
                                                             .name(employee.getName())
                                                             .build());
        return new Employee(saved.getId(), saved.getEmail(), saved.getName(), saved.getSex());
    }

    @Override
    public Employee readInfo(Long id) {
        Optional<EmployeeEntity> found = repository.findById(id);
        if(found.isEmpty()) return null;
        else {
            EmployeeEntity entity = found.get();
            return new Employee(entity.getId(), entity.getEmail(), entity.getName(), entity.getSex());
        }
    }

    @Override
    public List<Employee> readList() {
        List<EmployeeEntity> list = repository.findAll();
        return list.stream().map((entity) -> new Employee(entity.getId(), entity.getEmail(), entity.getName(), entity.getSex())).toList();
    }
}