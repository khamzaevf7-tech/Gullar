package uz.gullar.gullar.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email bo'sh bo'lishi mumkin emas")
        @Email(message = "Email formati noto'g'ri")
        String email,

        @NotBlank(message = "Password bo'sh bo'lishi mumkin emas")
        String password
) {
}
