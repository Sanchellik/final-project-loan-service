package ru.gozhan.loanservice.repository;

import java.util.Optional;

public interface UserRepository {

    Optional<Long> getUserIdByUsername(String username);

}
