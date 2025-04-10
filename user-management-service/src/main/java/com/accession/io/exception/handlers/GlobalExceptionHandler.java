package com.accession.io.exception.handlers;

import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-04 16:41:09
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<BaseResponse<?>> handleValidationException(ValidationException exception) {
        return BaseResponse.of(exception);
    }

    @ExceptionHandler(value = {RecordNotFoundException.class})
    public ResponseEntity<BaseResponse<?>> handleRecordNotFoundException(RecordNotFoundException exception) {
        return BaseResponse.of(exception);
    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<BaseResponse<?>> handleAuthenticationException(AuthenticationException exception) {
        return BaseResponse.of(exception);
    }

    @ExceptionHandler(value = {AuthorizationException.class})
    public ResponseEntity<BaseResponse<?>> handleAuthorizationException(AuthorizationException exception) {
        return BaseResponse.of(exception);
    }

    @ExceptionHandler(value = {AlreadyExistsException.class})
    public ResponseEntity<BaseResponse<?>> handleAlreadyExistsException(AlreadyExistsException exception) {
        return BaseResponse.of(exception);
    }
}
