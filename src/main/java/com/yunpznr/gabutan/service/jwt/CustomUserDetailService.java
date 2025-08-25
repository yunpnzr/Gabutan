package com.yunpznr.gabutan.service.jwt;

import com.yunpznr.gabutan.entity.User;
import com.yunpznr.gabutan.repository.auth.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User tidak ditemukan")
        );

        return new User(user.getEmail(), user.getPassword());
    }
}
