package com.yunpznr.gabutan.utils.jwt;

import com.yunpznr.gabutan.entity.User;
import com.yunpznr.gabutan.repository.auth.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {

        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);

                if (jwtUtils.validateToken(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                    String username = jwtUtils.getUsername(token);

                    if (username != null && !jwtUtils.isTokenExpired(token)) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        //User userDetails = userRepository.findFirstById(UUID.fromString(username)).orElseThrow();

                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                                //Collections.emptyList()
                        );

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }

        } catch (Exception e) {
            log.warn("Error: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
