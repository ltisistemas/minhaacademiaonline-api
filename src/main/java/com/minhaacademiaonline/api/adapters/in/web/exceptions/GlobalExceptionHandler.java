package com.minhaacademiaonline.api.adapters.in.web.exceptions;

import com.minhaacademiaonline.api.adapters.in.web.dto.ProblemDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> genericException(Exception ex, WebRequest req) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }

    @ExceptionHandler({
            UserNotFoundException.class,
            TenantNotFoundException.class,
            PlanNotFoundException.class,
            TenantNotFoundException.class,
            BeltNotFoundException.class
    })
    public ResponseEntity<ProblemDetail> notFoundException(RuntimeException ex, WebRequest req) {
        var status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }

    @ExceptionHandler(UserEmailExistsException.class)
    public ResponseEntity<ProblemDetail> emailExistsException(RuntimeException ex, WebRequest req) {
        var status = HttpStatus.CONFLICT;

        return new ResponseEntity<>(problemDetail(ex, req, status), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> argumentNotValidException(MethodArgumentNotValidException ex, WebRequest req) {
        var status = HttpStatus.BAD_REQUEST;

        List<String> errors = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return new ResponseEntity<>(problemDetail(ex, req, status, errors), status);
    }

    private ProblemDetail problemDetail(Exception ex, WebRequest req, HttpStatus status) {
        String instance = getInstance(req);

        return ProblemDetail.builder()
                .type(null)
                .title(status.getReasonPhrase())
                .code(status.value())
                .status(status.name())
                .detail(ex.getMessage())
                .instance(URI.create(instance))
                .errors(List.of(ex.getMessage()))
                .build();

    }
    private ProblemDetail problemDetail(Exception ex, WebRequest req, HttpStatus status, List<String> errors) {
        String instance = getInstance(req);

        return ProblemDetail.builder()
                .type(null)
                .title(status.getReasonPhrase())
                .code(status.value())
                .status(status.name())
                .detail(ex.getMessage())
                .instance(URI.create(instance))
                .errors(errors)
                .build();
    }
    private String getInstance(WebRequest req) {
        return ((ServletWebRequest) req).getRequest().getRequestURI();
    }

}
