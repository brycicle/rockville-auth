package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    Optional<Customer> findAllByIdIsNotNull();

}
