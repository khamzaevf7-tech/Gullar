package uz.gullar.gullar.auth.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gullar.gullar.auth.dto.LoginRequest;
import uz.gullar.gullar.auth.dto.LoginResponse;
import uz.gullar.gullar.auth.service.LoginService;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(loginService.login(request));
    }
}
