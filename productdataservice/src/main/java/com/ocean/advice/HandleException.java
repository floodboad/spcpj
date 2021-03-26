package com.ocean.advice;

import com.ocean.result.ExceptionEnum;
import com.ocean.result.Result;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class HandleException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        String message = (String)errorList.parallelStream().map((obj) -> {
            return obj.getDefaultMessage();
        }).findFirst().orElse("");
        System.out.println(message + "================");
        return this.failure(ExceptionEnum.HANDLER_SYSTEM_EXCEPTION.getErrorCode(),message);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e) {
        String message = e.getMessage();
        System.out.println(message + "================");
        return this.failure(ExceptionEnum.HANDLER_SYSTEM_EXCEPTION.getErrorCode(),message);
    }

    private Result failure(ExceptionEnum exceptionInfoEnum) {
        return Result.builder().success(false).error(exceptionInfoEnum).build();
    }

    private Result failure(String code, String message) {
        return Result.builder().success(false).error(code, message).build();
    }
}
