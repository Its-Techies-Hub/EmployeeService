package com.example;

import com.example.service.EmployeeService;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

public class DeleteEmployeeFunction {
    @FunctionName("deleteEmployee")
    public HttpResponseMessage run(
        @HttpTrigger(name = "req", methods = {HttpMethod.DELETE}, route = "employee/{id}", authLevel = AuthorizationLevel.FUNCTION)
        HttpRequestMessage<Optional<String>> request,
        @BindingName("id") String id,
        final ExecutionContext context
    ) {
        boolean deleted = EmployeeService.deleteEmployee(id);
        return request.createResponseBuilder(deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND).build();
    }
}
