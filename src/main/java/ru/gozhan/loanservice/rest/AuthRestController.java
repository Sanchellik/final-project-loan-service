//package ru.gozhan.loanservice.rest;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ru.gozhan.loanservice.dto.LoginDto;
//import ru.gozhan.loanservice.dto.RegisterDto;
//
//import java.util.Collections;
//
//@RestController
//@RequestMapping("/loan-service/auth")
//@AllArgsConstructor
//public class AuthRestController {
//
//    private UserRepository userRepository;
//    private AuthenticationManager authenticationManager;
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("login")
//    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginDto.getUsername(),
//                        loginDto.getPassword()
//        ));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        return new ResponseEntity<>("User signed in success!", HttpStatus.OK);
//    }
//
//    @PostMapping("register")
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
//
//        if (userRepository.existsByUsername(registerDto.getUsername())) {
//            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
//        }
//
//        User user = new User(registerDto.getUsername(),
//                passwordEncoder.encode(registerDto.getPassword()),
//                Collections.singleton(new SimpleGrantedAuthority("USER")));
//
//        userRepository.save(user);
//
//        return new ResponseEntity<>("User registered successful!", HttpStatus.OK);
//    }
//
//}
