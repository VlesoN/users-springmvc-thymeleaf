package com.project.services;

import com.project.models.User;
import com.project.repository.UserRepository;
import com.project.security.SpringSecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringServiceUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public SpringServiceUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SpringSecurityUserDetails(user);
    }
}
