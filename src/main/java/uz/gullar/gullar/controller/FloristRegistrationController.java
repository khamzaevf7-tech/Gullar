package uz.gullar.gullar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gullar.gullar.entity.User;
import uz.gullar.gullar.service.FloristRegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.gullar.gullar.dto.FloristRegistrationRequest;
import uz.gullar.gullar.dto.FloristRegistrationResponse;


@RestController
@RequestMapping("/api/v1/auth")
public class FloristRegistrationController {

    private  final FloristRegistrationService registrationService;
    public FloristRegistrationController(
            FloristRegistrationService floristRegistrationService
    ) {
        this.registrationService = floristRegistrationService;
    }

    @PostMapping("/florist/register")
    public ResponseEntity<FloristRegistrationResponse> register(
            @Valid @RequestBody FloristRegistrationRequest request
    ) {
        User user = registrationService.register(request);

        FloristRegistrationResponse response =
                new FloristRegistrationResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getStatus(),
                        user.getRoleId()
            );

        return ResponseEntity.status(201).body(response);
    }
}