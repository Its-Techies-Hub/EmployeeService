package com.example.service;

import com.example.model.Employee;
import java.util.*;

public class EmployeeService {
    private static final Map<String, Employee> EMP_DB = new HashMap<>();

    public static Employee addEmployee(Employee emp) {
        EMP_DB.put(emp.getId(), emp);
        return emp;
    }

    public static Employee getEmployee(String id) {
        return EMP_DB.get(id);
    }

    public static boolean deleteEmployee(String id) {
        return EMP_DB.remove(id) != null;
    }

    public static List<Employee> getAllEmployees() {
        return new ArrayList<>(EMP_DB.values());
    }
}
