package com.rockville.auth.repository;

import com.rockville.auth.model.domain.User;
import com.rockville.auth.repository.qdsl.QdslUserRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String>, QdslUserRepository {

    List<User> findAllByIdIsNotNull();
    Optional<User> findByIdEquals(String id);
    Optional<User> findByUsernameEqualsOrEmailEquals(String username, String email);
    Optional<User> findByUsernameEquals(String username);
    Optional<User> findByEmailEquals(String email);
    Optional<User> findByContactNumberEquals(String contactNumber);

}
