//package ru.gozhan.loanservice.auth;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class UserService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        return user;
//    }
//
//    public boolean addUser(User user) {
//        User userFromDb = userRepo.findByUsername(user.getUsername());
//
//        if (userFromDb != null) {
//            return false;
//        }
//
//        user.setActive(true);
//        user.setRoles(Collections.singleton(Role.USER));
//        user.setActivationCode(UUID.randomUUID().toString());
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        userRepo.save(user);
//
//        sendMessage(user);
//
//        return true;
//    }
//
//}
