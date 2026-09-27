package uz.gullar.gullar.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.gullar.gullar.dto.LoginRequest;
import uz.gullar.gullar.dto.LoginResponse;
import uz.gullar.gullar.entity.User;
import uz.gullar.gullar.repository.UserRepository;
import uz.gullar.gullar.exception.response.BusinessException;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.UNAUTHORIZED,
                        "INVALID_CREDENTIALS",
                        "Email yoki password noto'g'ri"
                ));

        if (!passwordEncoder.matches(
                request.password(),
                user.getPasswordHash()
        )) {
            throw new BusinessException(
                    HttpStatus.UNAUTHORIZED,
                    "INVALID_CREDENTIALS",
                    "Email yoki password noto'g'ri"
            );
        }

        if (!"ACTIVE".equals(user.getStatus())) {
            throw new BusinessException(
                    HttpStatus.FORBIDDEN,
                    "USER_NOT_ACTIVE",
                    "User accounti faol emas"
            );
        }

        return new LoginResponse(
                jwtService.generateToken(user.getEmail()),
                "Bearer"
        );
    }
}
