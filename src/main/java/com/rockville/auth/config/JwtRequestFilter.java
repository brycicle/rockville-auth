package com.rockville.auth.config;

import com.rockville.auth.model.dto.UserDetailsDto;
import com.rockville.auth.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final Optional<String> requestTokenHeaderOptional = Optional.ofNullable(request.getHeader("Authorization"));
        Optional<String> usernameOptional = Optional.empty();
        String jwtToken = "";
        if (requestTokenHeaderOptional.isPresent()) {

            jwtToken = requestTokenHeaderOptional.get().split(" ")[1];

            try {
                usernameOptional = Optional.ofNullable(jwtTokenUtil.getUsernameFromToken(jwtToken));
            } catch (final IllegalArgumentException illegalArgumentException) {
                logger.warn("Unable to get JWT Token");
            } catch (final ExpiredJwtException expiredJwtException) {
                logger.warn("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        if (usernameOptional.isPresent()) {
            final UserDetailsDto userDetails = (UserDetailsDto) userDetailsService.loadUserByUsername(usernameOptional.get());
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getUser().getAuthorities()
                        );

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
            }
        }
        filterChain.doFilter(request, response);
    }
}
