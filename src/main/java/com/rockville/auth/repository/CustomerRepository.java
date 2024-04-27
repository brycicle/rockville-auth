package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
    Optional<Customer> findAllByIdIsNotNull();
    Optional<Customer> findByFirstNameEquals(String firstName);
    Optional<Customer> findByMiddleNameEquals(String middleName);
    Optional<Customer> findByLastNameEquals(String lastName);
    Optional<Customer> findBySuffix(String suffix);
    Optional<Customer> findByEmailAddressEquals(String emailAddress);
    Optional<Customer> findByEmailEquals(String email);

}
