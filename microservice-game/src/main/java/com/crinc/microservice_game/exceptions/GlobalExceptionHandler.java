package com.crinc.microservice_game.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidGuessExeption.class)
    public ResponseEntity<ApiError> handleInvalidGuessException(InvalidGuessExeption ex, WebRequest req) {
        ApiError body = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("Invalid guess")
                .message(ex.getMessage())
                .path(req.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now())
                .details(null)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(NotPlayableException.class)
    public ResponseEntity<ApiError> handleNotPlayableException(NotPlayableException ex, WebRequest req) {
        ApiError body = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("Not playable")
                .build();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest req) {
        ApiError body = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .error("Invalid args")
                .message(ex.getMessage())
                .path(req.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now())
                .details(null)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest req) {
        ApiError body = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .error("Resource not found")
                .message(ex.getMessage())
                .path(req.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now())
                .details(null)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGlobalException(Exception ex, WebRequest req) {
        ApiError body = ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .error("Internal server error")
                .message("Exception not validated")
                .path(req.getDescription(false).replace("uri=", ""))
                .timestamp(Instant.now())
                .details(null)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}

