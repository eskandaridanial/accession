package com.accession.io.exception.handlers;

import com.accession.io.common.contracts.records.BaseResponse;
import com.accession.io.exception.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-10-04 16:41:09
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<BaseResponse<?>> handleAuthenticationException(AuthenticationException exception) {
        return BaseResponse.of(exception);
    }

    @ExceptionHandler(value = {HttpStatusCodeException.class})
    public ResponseEntity<BaseResponse<?>> handleHttpStatusCodeException(HttpStatusCodeException exception) {
        return BaseResponse.of(exception);
    }
}
