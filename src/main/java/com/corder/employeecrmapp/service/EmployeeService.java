package com.corder.employeecrmapp.service;

import com.corder.employeecrmapp.model.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    Page<Employee> listAll(int pageNum, String sortField, String sortDir, String keyword);
    void save(Employee employee);
    Employee get(Long id);
    void delete(Long id);
}
