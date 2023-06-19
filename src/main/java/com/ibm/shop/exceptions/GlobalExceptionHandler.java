package com.ibm.shop.exceptions;

import com.ibm.shop.data.vo.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDetails errorDetails = new ErrorDetails(
                Instant.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                status.value()
        );

        return ResponseEntity.status(status).body(errorDetails);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(
            BlogAPIException exception,
            WebRequest webRequest
    ) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorDetails errorDetails = new ErrorDetails(
                Instant.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                status.value()
        );

        return ResponseEntity.status(status).body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest webRequest
    ) {

        Map<String, String> errors = new HashMap<>();

        List<String> errorMessages = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            errors.put(fieldName, message);

            errorMessages.add(message);
        });

        String concatenedErrors = String.join(",", errorMessages);

        ErrorDetails errorDetails = new ErrorDetails(
                Instant.now(),
                concatenedErrors,
                webRequest.getDescription(false),
                status.value()
        );

        return ResponseEntity.status(status).body(errorDetails);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(
            AccessDeniedException exception,
            WebRequest webRequest
    ) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;

        ErrorDetails errorDetails = new ErrorDetails(
                Instant.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                status.value()
        );

        return ResponseEntity.status(status).body(errorDetails);
    }

}
