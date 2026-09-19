package uz.gullar.gullar.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.gullar.gullar.auth.entity.User;
import uz.gullar.gullar.auth.repository.UserRepository;
import uz.gullar.gullar.common.exception.BusinessException;

@Service
public class CurrentUserService {

    private final UserRepository userRepository;

    public CurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND,
                        "USER_NOT_FOUND",
                        "User topilmadi"
                ));
    }
}

