package uz.gullar.gullar.auth.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gullar.gullar.auth.dto.CurrentUserResponse;
import uz.gullar.gullar.auth.entity.User;
import uz.gullar.gullar.auth.service.CurrentUserService;

import java.util.spi.CurrencyNameProvider;

@RestController
@RequestMapping("/api/v1/auth")
public class CurrentUserController {

    private final CurrentUserService currentUserService;

    public CurrentUserController(CurrentUserService currentUserService)
{
        this.currentUserService = currentUserService;
    }

    @GetMapping("/me")
    public ResponseEntity<CurrentUserResponse> getCurrentUser(
            Authentication authentication
    ) {
        User user = currentUserService.getCurrentUser(
                authentication.getName()
        );

        CurrentUserResponse response = new CurrentUserResponse(
                user.getId(),
                user.getEmail(),
                user.getPhone(),
                user.getStatus(),
                user.getRoleId()
        );

        return ResponseEntity.ok(response);
    }
}
