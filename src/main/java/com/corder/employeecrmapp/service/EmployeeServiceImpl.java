package com.corder.employeecrmapp.service;

import com.corder.employeecrmapp.model.Employee;
import com.corder.employeecrmapp.repository.EmployeeRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Page<Employee> listAll(int pageNum, String sortField, String sortDir, String keyword) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, 5, sort);

        if (keyword != null) {
            return employeeRepository.findAll(keyword, pageable);
        }
        return employeeRepository.findAll(pageable);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee get(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public long getTotalCountOfEmployeesByStatus(String status) {
        return employeeRepository.countByStatus(status);
    }

    @Override
    public long getTotalCount() {
        return employeeRepository.count();
    }
}
