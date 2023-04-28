package ru.gozhan.loanservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gozhan.loanservice.model.User;
import ru.gozhan.loanservice.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUsername(username);

        return userOptional.orElseThrow(
                () -> new UsernameNotFoundException("User with username " + username + " not found."));
    }
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<User> userOptional = userRepository.findByUsername(username);
//
//        if (userOptional.isEmpty()) {
//            throw new UsernameNotFoundException("User not found with username: " + username);
//        }
//        return userOptional.get();
//    }

}
