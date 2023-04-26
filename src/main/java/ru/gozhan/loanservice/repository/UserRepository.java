package ru.gozhan.loanservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gozhan.loanservice.security.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    User save(User user);

}
