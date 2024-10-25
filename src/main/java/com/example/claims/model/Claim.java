package com.example.claims.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "claims")
@EntityListeners(AuditingEntityListener.class)
public class Claim {
    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "uuid")
    @GeneratedValue
    private UUID id;

    @Column(name = "code", unique = true, nullable = false, length = 9)
    private String code;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    private List<String> attachments = new ArrayList<>();

    @Column(name = "customer_email", nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String status;

    @CreatedDate
    @Column(name = "creation_date", updatable = false, nullable = false)
    private LocalDateTime creationDate;

    @CreatedBy
    @Column(name = "creation_user", updatable = false, nullable = false)
    private String creationUser;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @LastModifiedBy
    @Column(name = "update_user")
    private String updateUser;
}