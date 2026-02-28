package com.org.invmgm.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Referenced Most common HTTP response status code
/*
    OK(200, HttpStatus.Series.SUCCESSFUL, "OK")
    CREATED(201, HttpStatus.Series.SUCCESSFUL, "Created")
    ACCEPTED(202, HttpStatus.Series.SUCCESSFUL, "Accepted")
    NO_CONTENT(204, HttpStatus.Series.SUCCESSFUL, "No Content")
    UNAUTHORIZED(401, HttpStatus.Series.CLIENT_ERROR, "Unauthorized")
    FORBIDDEN(403, HttpStatus.Series.CLIENT_ERROR, "Forbidden")
    NOT_FOUND(404, HttpStatus.Series.CLIENT_ERROR, "Not Found")
    REQUEST_TIMEOUT(408, HttpStatus.Series.CLIENT_ERROR, "Request Timeout")
    CONFLICT(409, HttpStatus.Series.CLIENT_ERROR, "Conflict")
    CONTENT_TOO_LARGE(413, HttpStatus.Series.CLIENT_ERROR, "Content Too Large")
*/

    /*@ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> dataNotFound(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult().getFieldError().getDefaultMessage();

        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {

        String message = ex.getConstraintViolations()
                .iterator()
                .next()
                .getMessage();

        return ResponseEntity.badRequest().body(Map.of("message", message));
    }

    // Simple exception response
    /*@ExceptionHandler(DeleteNotAllowedException.class)
    public ResponseEntity<?> handleDeleteNotAllowed(DeleteNotAllowedException ex) {
        // 409 status code
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }*/

    // production style exception response
    @ExceptionHandler(DeleteNotAllowedException.class)
    public ResponseEntity<ApiErrorResponse> handleDeleteNotAllowed(DeleteNotAllowedException ex, HttpServletRequest request) {
        // 409 status code
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.CONFLICT.value(),ex.getMessage(),HttpStatus.CONFLICT.getReasonPhrase(), LocalDateTime.now(),request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundValue(DataNotFoundException ex, HttpServletRequest request) {
        // 404 status code
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                HttpStatus.NOT_FOUND.value(),ex.getMessage(),HttpStatus.NOT_FOUND.getReasonPhrase(), LocalDateTime.now(),request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
