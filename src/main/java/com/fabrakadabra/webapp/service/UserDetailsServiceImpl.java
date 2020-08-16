package com.fabrakadabra.webapp.service;

import com.fabrakadabra.webapp.model.Admin;
import com.fabrakadabra.webapp.repository.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private AdminRepository adminRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String adminName) throws UsernameNotFoundException {
        Optional<Admin> adminOptional = adminRepository.findByName(adminName);
        Admin admin = adminOptional.orElseThrow(() -> new UsernameNotFoundException("No admin " +
                "Found with name " + adminName));

        return new User(admin.getName(),admin.getPassword(),admin.isEnabled(),
                true,
                true,
                true,
                getAuthorities("ADMIN"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role){
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
