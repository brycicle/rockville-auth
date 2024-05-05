package com.rockville.auth.controller;

import brave.Tracer;
import com.rockville.auth.model.dto.BaseErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public final class ExceptionController {

    private final Tracer tracer;
    @ExceptionHandler({
            BadCredentialsException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<BaseErrorResponse> validationException(
            final HandlerMethod handlerMethod,
            final BadCredentialsException exception
    ) {
        log("BadCredentialsException", handlerMethod, exception);
        return new ResponseEntity<>(
                new BaseErrorResponse(
                        exception.getMessage()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }

    public void log(final String content, final HandlerMethod handlerMethod, final Exception exception) {
        log.error("[{}] - {} : {}", content, handlerMethod.getMethod().getDeclaringClass(), exception.getMessage());
    }
}
