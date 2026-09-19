package uz.gullar.gullar.auth.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.gullar.gullar.auth.dto.FloristRegistrationRequest;
import uz.gullar.gullar.auth.entity.Role;
import uz.gullar.gullar.auth.entity.User;
import uz.gullar.gullar.auth.repository.RoleRepository;
import uz.gullar.gullar.auth.repository.UserRepository;
import uz.gullar.gullar.common.exception.BusinessException;

@Service
public class FloristRegistrationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public FloristRegistrationService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(FloristRegistrationRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException(
                    HttpStatus.CONFLICT,
                    "EMAIL_ALREADY_EXISTS",
                    "Bu Email allaqachon ro'yxatdan o'tgan"
            );
        }
        Role floristRole = roleRepository.findByName("FLORIST")
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "FLORIST_ROLE_NOT_FOUND",
                        "FLORIST roli database'da topilmadi"
                ));
        String passwordHash = passwordEncoder.encode(request.password());

        User florist = new User(
                request.email(),
                request.phone(),
                passwordHash,
                "PENDING",
                floristRole.getId()
        );

        return userRepository.save(florist);
    }
}