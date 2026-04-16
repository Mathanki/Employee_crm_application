package com.corder.employeecrmapp.controller;

import com.corder.employeecrmapp.model.Employee;
import com.corder.employeecrmapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService service;
    @GetMapping("/")
    public String viewHomePage(Model model,
                               @RequestParam(name = "keyword", required = false) String keyword,
                               @RequestParam(name = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(name = "sortField", defaultValue = "firstName") String sortField,
                               @RequestParam(name = "sortDir", defaultValue = "asc") String sortDir) {

        Page<Employee> page = service.listAll(pageNum, sortField, sortDir, keyword);

        model.addAttribute("listEmployees", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        service.save(employee);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("employee", service.get(id));
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        service.delete(id);
        return "redirect:/";
    }
}
