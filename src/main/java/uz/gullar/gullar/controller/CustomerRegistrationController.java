package uz.gullar.gullar.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.gullar.gullar.dto.CustomerRegistrationRequest;
import uz.gullar.gullar.dto.CustomerRegistrationResponse;
import uz.gullar.gullar.entity.User;
import uz.gullar.gullar.service.CustomerRegistrationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
public class CustomerRegistrationController {

    private final CustomerRegistrationService registrationService;

    public CustomerRegistrationController(
            CustomerRegistrationService registrationService
    ) {
        this.registrationService = registrationService;
    }
    @PostMapping("/register")
    public ResponseEntity<CustomerRegistrationResponse> register(
            @Valid @RequestBody CustomerRegistrationRequest request
            ) {
        User user = registrationService.register(request);

        CustomerRegistrationResponse response =
                new CustomerRegistrationResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getPhone(),
                        user.getStatus(),
                        user.getRoleId()
                );

        return ResponseEntity.status(201).body(response);
    }
}

