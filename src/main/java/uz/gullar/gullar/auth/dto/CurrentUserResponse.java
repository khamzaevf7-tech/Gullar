package uz.gullar.gullar.auth.dto;

public record CurrentUserResponse(
        Long id,
        String email,
        String phone,
        String status,
        Long roleId
) {
}
