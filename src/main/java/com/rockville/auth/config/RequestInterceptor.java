package com.rockville.auth.config;

import brave.Tracer;
import com.rockville.auth.model.dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Component
@RequiredArgsConstructor
@ControllerAdvice
public class RequestInterceptor implements ResponseBodyAdvice<BaseResponse<?>> {

    private final Tracer tracer;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public BaseResponse<?> beforeBodyWrite(
            BaseResponse<?> body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        body.setTraceId(tracer.currentSpan().context().toString().replace("/","-"));
        return body;
    }
}