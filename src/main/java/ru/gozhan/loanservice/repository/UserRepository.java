package ru.gozhan.loanservice.repository;

import ru.gozhan.loanservice.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

}
