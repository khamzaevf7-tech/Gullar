package uz.gullar.gullar.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.gullar.gullar.admin.dto.FloristApprovalResponse;
import uz.gullar.gullar.admin.service.FloristApprovalService;
import uz.gullar.gullar.auth.entity.User;

@RestController
@RequestMapping("/api/v1/admin/florists")
public class FloristApprovalController {

    private final FloristApprovalService floristApprovalService;

    public FloristApprovalController(
            FloristApprovalService floristApprovalService
    ) {
        this.floristApprovalService = floristApprovalService;
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<FloristApprovalResponse> approve(
            @PathVariable Long id
    ) {
        User user = floristApprovalService.approve(id);

        return ResponseEntity.ok(
                new FloristApprovalResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getStatus()
                )
        );
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<FloristApprovalResponse> reject(
            @PathVariable Long id
    ) {
        User user = floristApprovalService.reject(id);

        return ResponseEntity.ok(
                new FloristApprovalResponse(
                        user.getId(),
                        user.getEmail(),
                        user.getStatus()
                )
        );
    }
}