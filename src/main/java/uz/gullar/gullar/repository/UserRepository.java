package uz.gullar.gullar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullar.gullar.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}

