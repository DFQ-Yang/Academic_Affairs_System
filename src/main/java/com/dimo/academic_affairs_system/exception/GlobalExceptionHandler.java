package com.dimo.academic_affairs_system.exception;

import com.dimo.academic_affairs_system.pojo.Result;
import com.dimo.academic_affairs_system.pojo.StandardException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(StandardException.class)
    public Result StandardExceptionHandler(StandardException e){
        log.warn("StandardException happened: {}", e.getMsg());
        return Result.error(e.getCode(), e.getMsg());
    }

    /*@ResponseBody
    @ExceptionHandler(Exception.class)
    public Result GeneralExceptionHandler(Exception e){
        log.warn("未知错误发生");
        return Result.error("未知错误发生");
    }*/
}
