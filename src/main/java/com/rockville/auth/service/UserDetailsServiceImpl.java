package com.rockville.auth.service;

import com.rockville.auth.model.domain.User;
import com.rockville.auth.model.dto.UserDetailsDto;
import com.rockville.auth.repository.RoleRepository;
import com.rockville.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public final class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    public UserDetailsDto loadUserByUsername(final String username) throws UsernameNotFoundException {

        final Optional<User> accountOptional = userRepository.findByUsernameEquals(username);

        if (accountOptional.isPresent()) {
            final User user = accountOptional.get();

            return UserDetailsDto.builder()
                    .id(user.getId())
                    .firstName(user.getFirstName())
                    .middleName(user.getMiddleName())
                    .lastName(user.getLastName())
                    .user(new org.springframework.security.core.userdetails.User(
                                    user.getUsername(),
                                    user.getPassword(),
                                    roleRepository.findAllByUserIdEquals(user.getId()).stream()
                                            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                                            .collect(Collectors.toList())
                            )
                    )
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
