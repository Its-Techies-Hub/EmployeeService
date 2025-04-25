package com.example;

import com.example.service.EmployeeService;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

public class GetEmployeeFunction {
    @FunctionName("getEmployee")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, route = "employee/{id}", authLevel = AuthorizationLevel.FUNCTION)
        HttpRequestMessage<Optional<String>> request,
        @BindingName("id") String id,
        final ExecutionContext context
    ) {
        var emp = EmployeeService.getEmployee(id);
        if (emp == null) {
            return request.createResponseBuilder(HttpStatus.NOT_FOUND).body("Employee not found").build();
        }
        return request.createResponseBuilder(HttpStatus.OK).body(emp).build();
    }
}
