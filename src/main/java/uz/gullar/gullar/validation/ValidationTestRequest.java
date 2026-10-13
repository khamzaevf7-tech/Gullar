package uz.gullar.gullar.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ValidationTestRequest(

        @NotBlank(message = "Name bo'sh bo'lishi mumkin emas")
        String name,

        @NotBlank(message = "Email bo'sh bo'lishi mumkin emas")
        @Email(message = "Email formati noto'g'ri")
        String email,

        @NotBlank(message = "Password bo'sh bo'lishi mumkin emas")
        @Size(min = 8, message = "Password kamida 8 ta belgidan iborat bo'lishi kerak")
        String password
) {
}