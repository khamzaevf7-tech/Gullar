package uz.gullar.gullar.auth.dto;

public record CustomerRegistrationResponse(
        Long id,
        String email,
        String phone,
        String status,
        Long roleId
) {
}

