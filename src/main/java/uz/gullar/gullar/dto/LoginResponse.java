package uz.gullar.gullar.dto;

public record LoginResponse(
        String accessToken,
        String tokenType
) {
}
