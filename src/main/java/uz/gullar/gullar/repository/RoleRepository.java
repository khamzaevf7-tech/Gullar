package uz.gullar.gullar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.gullar.gullar.entity.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
