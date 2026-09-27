package uz.gullar.gullar.dto;

public record FloristRegistrationResponse(
        Long id,
        String email,
        String phone,
        String status,
        Long roleId
) {
}
