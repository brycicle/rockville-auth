package com.rockville.auth.service;

import com.rockville.auth.model.domain.Customer;
import com.rockville.auth.model.dto.*;
import com.rockville.auth.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Override
    public List<CustomerResponse> getCustomers() {
        return repository.findAllByIdIsNotNull()
                .stream()
                .map(customer -> CustomerResponse.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .middleName(customer.getMiddleName())
                        .lastName(customer.getLastName())
                        .suffix(customer.getSuffix())
                        .emailAddress(customer.getEmailAddress())
                        .address(customer.getAddress())
                        .createdBy(customer.getCreatedBy())
                        .createdAt(customer.getCreatedAt())
                        .updatedBy(customer.getUpdatedBy())
                        .updatedAt(customer.getUpdatedAt())
                        .build()
                )
                .collect(Collectors.toList());

    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        Customer customer = repository.save(
                Customer.builder()
                        .firstName(request.getFirstName())
                        .middleName(request.getMiddleName())
                        .lastName(request.getLastName())
                        .suffix(request.getSuffix())
                        .emailAddress(request.getEmailAddress())
                        .contactNumber(request.getContactNumber())
                        .address(request.getAddress())
                        .type(request.getType())
                        .maritalStatus(request.getMaritalStatus())
                        .gender(request.getGender())
                        .build()
        );
        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .middleName(customer.getMiddleName())
                .lastName(customer.getLastName())
                .suffix(customer.getSuffix())
                .emailAddress(customer.getEmailAddress())
                .contactNumber(customer.getContactNumber())
                .address(customer.getAddress())
                .type(customer.getType())
                .createdBy(customer.getCreatedBy())
                .createdAt(customer.getCreatedAt())
                .updatedBy(customer.getUpdatedBy())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }
}
