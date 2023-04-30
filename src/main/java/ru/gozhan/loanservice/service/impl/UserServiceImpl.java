package ru.gozhan.loanservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gozhan.loanservice.repository.UserRepository;
import ru.gozhan.loanservice.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long getUserIdByUsername(String username) {
        return userRepository.getUserIdByUsername(username);
    }

}
