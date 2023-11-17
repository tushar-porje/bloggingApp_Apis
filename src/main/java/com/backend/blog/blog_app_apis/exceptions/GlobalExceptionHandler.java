package com.backend.blog.blog_app_apis.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.blog.blog_app_apis.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourseNotFoundException(ResourceNotFoundException rs){
        String message=rs.getMessage();
        ApiResponse apiResponse=new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptio(MethodArgumentNotValidException ex){
        Map<String,String> mp=new HashMap<>();
        // ex.getFieldErrors().forEach(err->{
        //     String name=err.getField();
        //     String mess=err.getDefaultMessage();
        //     mp.put(name, mess);
        // });
        ex.getBindingResult().getFieldErrors().forEach(err->{
            String name=((FieldError)err).getField();
            String mess=err.getDefaultMessage();
            mp.put(name, mess);
        });
        return new ResponseEntity<>(mp,HttpStatus.BAD_REQUEST);
    }
}
