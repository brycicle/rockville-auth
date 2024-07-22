package com.rockville.auth.repository.qdsl;

import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.rockville.auth.model.domain.Role;
import com.rockville.auth.model.domain.User;
import com.rockville.auth.model.dto.UserResponse;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.rockville.auth.model.domain.QRole.role;
import static com.rockville.auth.model.domain.QUser.user;

public class QdslUserRepositoryImpl extends QuerydslRepositorySupport implements QdslUserRepository {
    JPAQueryFactory queryFactory;

    @Autowired
    public QdslUserRepositoryImpl(EntityManager entityManager) {
        super(User.class);
        setEntityManager(entityManager);
        queryFactory = new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }

    @Override
    public List<User> getSalesAgents() {
        return queryFactory.select(user)
                .from(user)
                .join(role)
                .on(role.userId.eq(user.id))
                .where(role.name.eq("Sales_Agent"))
                .stream()
                .sorted(Comparator.comparing(User::getCreatedAt))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getUsers() {
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        queryFactory.select(user, role)
                .from(user)
                .join(role)
                .on(role.userId.eq(user.id))
                .where(role.name.ne("Sales_Agent"))
                .stream()
                .forEach(
                        tuple -> {
                            users.add(tuple.get(user));
                            roles.add(tuple.get(role));
                        }
                );
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(
                        UserResponse.builder()
                                .id(user.getId())
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .contactNumber(user.getContactNumber())
                                .firstName(user.getFirstName())
                                .middleName(user.getMiddleName())
                                .lastName(user.getLastName())
                                .createdBy(user.getCreatedBy())
                                .createdAt(user.getCreatedAt())
                                .updatedBy(user.getUpdatedBy())
                                .updatedAt(user.getUpdatedAt())
                                .roles(roles.stream()
                                        .filter(role -> role.getUserId().equals(user.getId()))
                                        .map(Role::getName)
                                        .collect(Collectors.toList())
                                )
                                .build()
                )
        );

        return userResponses;
    }
}
