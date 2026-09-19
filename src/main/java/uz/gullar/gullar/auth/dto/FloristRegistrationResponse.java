package uz.gullar.gullar.auth.dto;

public record FloristRegistrationResponse(
        Long id,
        String email,
        String phone,
        String status,
        Long roleId
) {
}
