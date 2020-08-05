package com.thoughtworks.todoList.config;

import com.thoughtworks.todoList.constant.ExceptionMessage;
import com.thoughtworks.todoList.exception.IllegalOperationException;
import com.thoughtworks.todoList.exception.NoSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    //todo need param
    String handleNoSuchDataException() {
        return ExceptionMessage.NO_SUCH_DATA.getMessage();
    }

    @ExceptionHandler(IllegalOperationException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String handleIllegalOperationException() {
        return ExceptionMessage.ILLEGAL_OPERATION.getMessage();
    }

}
