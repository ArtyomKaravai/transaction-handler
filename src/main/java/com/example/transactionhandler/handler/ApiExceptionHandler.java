package com.example.transactionhandler.handler;

import com.example.transactionhandler.exception.TransactionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleTransactionNotFoundException(TransactionNotFoundException exception) {
        return exception.getMessage();
    }
}
