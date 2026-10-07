package uz.gullar.gullar.admin.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.gullar.gullar.auth.entity.Role;
import uz.gullar.gullar.auth.entity.User;
import uz.gullar.gullar.auth.repository.RoleRepository;
import uz.gullar.gullar.auth.repository.UserRepository;
import uz.gullar.gullar.common.exception.BusinessException;

@Service
public class FloristApprovalService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public FloristApprovalService(
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Transactional
    public User approve(Long userId) {
        return updatePendingFloristStatus(userId, "ACTIVE");
    }

    @Transactional
    public User reject(Long userId) {
        return updatePendingFloristStatus(userId, "REJECTED");
    }

    private User updatePendingFloristStatus(
            Long userId,
            String newStatus
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.NOT_FOUND,
                        "USER_NOT_FOUND",
                        "User topilmadi"
                ));
        Role floristRole = roleRepository.findByName("FLORIST")
                .orElseThrow(() -> new BusinessException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "FLORIST_ROLE_NOT_FOUND",
                        "FLORIST roli database'da topilmadi"
                ));

        if (!floristRole.getId().equals(user.getRoleId())) {
            throw new BusinessException(
                    HttpStatus.BAD_REQUEST,
                    "USER_IS_NOT_A_FLORIST",
                    "Bu user florist emas"
            );
        }

        if (!"PENDING".equals(user.getStatus())) {
            throw new BusinessException(
                    HttpStatus.CONFLICT,
                    "FLORIST_NOT_PENDING",
                    "Faqat PENDING floristni ko'rib chiqish mumkin"
            );
        }

        user.updateStatus(newStatus);
        return user;
    }
}
