package com.yunpznr.gabutan.service.jwt;

import com.yunpznr.gabutan.entity.User;
import com.yunpznr.gabutan.repository.auth.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UUID userId = UUID.fromString(username);

            User user = userRepository.findById(userId).orElseThrow(
                    //() -> new UsernameNotFoundException("User tidak ditemukan")
            );

            return new User(user.getId(), user.getPassword());
    }
}
