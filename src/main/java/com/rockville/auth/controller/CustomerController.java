package com.rockville.auth.controller;

import com.rockville.auth.model.dto.*;
import com.rockville.auth.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    @GetMapping
    public BaseResponse<List<CustomerResponse>> getCustomers() {
        log.info("CustomerController - getCustomers");
        return new BaseResponse<>(
                customerService.getCustomers()
        );
    }
    @PostMapping
    public BaseResponse<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        log.info("CustomerController - createCustomer {}", request);
        return new BaseResponse<>(
                customerService.createCustomer(request)
        );
    }
}
