package com.rockville.auth.service;

import com.rockville.auth.model.dto.CustomerRequest;
import com.rockville.auth.model.dto.CustomerResponse;
import com.rockville.auth.model.dto.LotRequest;
import com.rockville.auth.model.dto.LotResponse;

import java.util.List;


public interface CustomerService {
    List<CustomerResponse> getCustomers();
    CustomerResponse createCustomer(CustomerRequest request);
}
