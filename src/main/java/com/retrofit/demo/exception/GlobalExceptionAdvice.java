package com.retrofit.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionAdvice {
    //处理未知异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request, Exception e) {
        UnifyResponse unifyResponse = new UnifyResponse("999999", e.getMessage(), request.getRequestURI());
        System.out.println(unifyResponse);
        return unifyResponse;
    }
}

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class UnifyResponse {
    private String code;
    private String message;
    private String request;
}
