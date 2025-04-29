package com.br.app.vehicles.java.infra.controller.http;

import com.br.app.vehicles.java.infra.controller.jsons.responses.ErrorResponse;
import com.br.app.vehicles.java.infra.exceptions.VehicleNotSaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(final Exception ex, final WebRequest request) {
        final ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.internalServerError().body(response);
    }

    @ExceptionHandler(VehicleNotSaveException.class)
    public ResponseEntity<?> handleVehicleNotSaveException(final VehicleNotSaveException ex, final WebRequest request) {
        final ErrorResponse response = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(final MethodArgumentNotValidException ex, final WebRequest request) {

        final ObjectError objectError = ex.getBindingResult().getAllErrors()
                .stream()
                .findFirst()
                .orElseThrow();

        final String message = objectError.getDefaultMessage();

        final var response = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), message, request.getDescription(false));
        return ResponseEntity.badRequest().body(response);
    }

}
