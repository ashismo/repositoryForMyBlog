package com.ashish.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ashish.exception.CustomException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler({CustomException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public Map<String, String> unauthorizedAccess(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();

        System.out.println("unauthorized Access to the API: " + e.getMessage());
        exception.put("code", "401");
        exception.put("reason", e.getMessage());

        return exception;
    }
    
    @ExceptionHandler({Exception.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
    public Map<String, String> exceptionInProcessing(Exception e) {
        Map<String, String> exception = new HashMap<String, String>();

        System.out.println("Unable to process. Some error occured: " + e.getMessage());
        exception.put("code", "417");
        exception.put("reason", e.toString());

        return exception;
    }
}
