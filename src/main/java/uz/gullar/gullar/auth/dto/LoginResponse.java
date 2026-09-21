package uz.gullar.gullar.auth.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {
}
