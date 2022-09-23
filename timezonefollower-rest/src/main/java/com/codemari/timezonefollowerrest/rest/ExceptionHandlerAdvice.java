package com.codemari.timezonefollowerrest.rest;

import com.codemari.timezonefollowerrest.exception.DuplicatedFavouriteLocationException;
import com.codemari.timezonefollowerrest.exception.DuplicatedUserException;
import com.codemari.timezonefollowerrest.exception.LocationNotFoundException;
import com.codemari.timezonefollowerrest.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Optional.ofNullable;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ApiCallError<T> {

        private String message;
        private List<T> details;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiCallError<String>> userNotFoundHandler(
            HttpServletRequest request, UserNotFoundException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiCallError<>("Not found exception", List.of(e.getMessage())));
    }

    @ExceptionHandler(DuplicatedUserException.class)
    public ResponseEntity<ApiCallError<String>> duplicatedUserExceptionHandler(
            HttpServletRequest request, DuplicatedUserException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiCallError<>("Duplicated user exception", List.of(e.getMessage())));
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ApiCallError<String>> locationNotFoundHandler(
            HttpServletRequest request, DuplicatedUserException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiCallError<>("Location not found exception", List.of(e.getMessage())));
    }

    @ExceptionHandler(DuplicatedFavouriteLocationException.class)
    public ResponseEntity<ApiCallError<String>> duplicatedFavouriteLocationExceptionHandler(
            HttpServletRequest request, DuplicatedFavouriteLocationException e) {
        logger.error(request.getRequestURI() + " " + e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiCallError<>("Duplicated favourite location exception", List.of(e.getMessage())));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiCallError<String>> handleValidationException(
            HttpServletRequest request, ValidationException e) {
        logger.error("ValidationException {}", request.getRequestURI(), e);

        return ResponseEntity.badRequest()
                .body(new ApiCallError<>("Validation exception", List.of(e.getMessage())));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiCallError<String>> handleMissingServletRequestParameterException(
            HttpServletRequest request, MissingServletRequestParameterException e) {
        logger.error("handleMissingServletRequestParameterException {}", request.getRequestURI(), e);

        return ResponseEntity.badRequest()
                .body(new ApiCallError<>("Missing request parameter", List.of(e.getMessage())));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiCallError<Map<String, String>>> handleMethodArgumentTypeMismatchException(
            HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        logger.error("handleMethodArgumentTypeMismatchException {}", request.getRequestURI(), e);

        var details = new HashMap<String, String>();
        details.put("paramName", e.getName());
        details.put("paramValue", ofNullable(e.getValue()).map(Object::toString).orElse(""));
        details.put("errorMessage", e.getMessage());

        return ResponseEntity.badRequest()
                .body(new ApiCallError<>("Method argument type mismatch", List.of(details)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiCallError<Map<String, String>>> handleMethodArgumentNotValidException(
            HttpServletRequest request, MethodArgumentNotValidException e) {
        logger.error("handleMethodArgumentNotValidException {}", request.getRequestURI(), e);

        var details = new ArrayList<Map<String, String>>();
        e.getBindingResult()
                .getFieldErrors()
                .forEach(
                        fieldError -> {
                            Map<String, String> detail = new HashMap<>();
                            detail.put("objectName", fieldError.getObjectName());
                            detail.put("field", fieldError.getField());
                            detail.put("rejectedValue", "" + fieldError.getRejectedValue());
                            detail.put("errorMessage", fieldError.getDefaultMessage());
                            details.add(detail);
                        });

        return ResponseEntity.badRequest()
                .body(new ApiCallError<>("Method argument validation failed", details));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiCallError<String>> handleAccessDeniedException(
            HttpServletRequest request, AccessDeniedException ex) {
        logger.error("handleAccessDeniedException {}", request.getRequestURI(), ex);

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiCallError<>("Access denied!", List.of(ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiCallError<String>> handleInternalServerError(
            HttpServletRequest request, Exception e) {
        logger.error("handleInternalServerError {}", request.getRequestURI(), e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiCallError<>("Internal server error", List.of(e.getMessage())));
    }
}
