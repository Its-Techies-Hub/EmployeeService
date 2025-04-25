package com.example;

import com.example.service.EmployeeService;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

public class ListEmployeesFunction {
    @FunctionName("listEmployees")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.GET}, route = "employees", authLevel = AuthorizationLevel.FUNCTION)
        HttpRequestMessage<Optional<String>> request,
        final ExecutionContext context
    ) {
        return request.createResponseBuilder(HttpStatus.OK).body(EmployeeService.getAllEmployees()).build();
    }
}
