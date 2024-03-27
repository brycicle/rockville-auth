package com.rockville.auth.repository;

import com.rockville.auth.model.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, String> {
    List<Role> findAllByIdIsNotNull();
}
