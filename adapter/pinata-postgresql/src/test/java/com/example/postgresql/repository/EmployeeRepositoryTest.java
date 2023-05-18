package com.example.postgresql.repository;

import com.example.postgresql.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Test
    void test() {
        String name = "test";
        var result = employeeRepository.save(Employee.builder()
                                                     .email("test@gmail.com")
                                                     .sex("male")
                                                     .name(name)
                                                     .build());
        assertEquals(name, result.getName());
    }
}