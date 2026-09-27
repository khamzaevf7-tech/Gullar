package uz.gullar.gullar.validation.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gullar.gullar.validation.ValidationTestRequest;

@RestController
@RequestMapping("/api/v1/validation-test")
public class ValidationTestController {

    @PostMapping
    public ResponseEntity<ValidationTestRequest> validate(
            @Valid @RequestBody ValidationTestRequest request
    ) {
        return ResponseEntity.ok(request);
    }
}
