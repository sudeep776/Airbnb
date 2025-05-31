package com.springboot.project.airBnbApp.advice;

import com.springboot.project.airBnbApp.exception.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError apiError = ApiError.builder().status(HttpStatus.NOT_FOUND)
                .message(exception.getMessage()).build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(Exception exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(AuthenticationException exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(JwtException exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.UNAUTHORIZED)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleInternalServerError(AccessDeniedException exception){
        ApiError apiError = ApiError.builder()
                .message(exception.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError),apiError.getStatus());
    }
}
