//package ru.gozhan.loanservice.security;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.provisioning.JdbcUserDetailsManager;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final JdbcUserDetailsManager jdbcUserDetailsManager;
//
//    public UserDetailsServiceImpl(JdbcUserDetailsManager jdbcUserDetailsManager) {
//        this.jdbcUserDetailsManager = jdbcUserDetailsManager;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails userDetails = jdbcUserDetailsManager.loadUserByUsername(username);
//        if (userDetails == null) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return userDetails;
//    }
//
//}
