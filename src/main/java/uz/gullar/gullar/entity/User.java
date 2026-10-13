package uz.gullar.gullar.entity;

import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String status;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    protected User() {
    }
    public User(
            String email,
            String phone,
            String passwordHash,
            String status,
            Long roleId
    ) {
        this.email = email;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.status = status;
        this.roleId = roleId;
        this.createdAt = OffsetDateTime.now();
        this.updatedAt = OffsetDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
        this.updatedAt = OffsetDateTime.now();
    }

    public Long getRoleId() {
        return roleId;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }
}

