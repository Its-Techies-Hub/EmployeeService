package com.example;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.Optional;

public class AddEmployeeFunction {
    @FunctionName("addEmployee")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.FUNCTION)
        HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context
    ) {
        try {
            Employee emp = new ObjectMapper().readValue(request.getBody().orElse(""), Employee.class);
            EmployeeService.addEmployee(emp);
            return request.createResponseBuilder(HttpStatus.CREATED)
                .body("Employee added: " + emp.getName()).build();
        } catch (Exception e) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage()).build();
        }
    }
}
