package com.example.claims.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "claim_status")
@EntityListeners(AuditingEntityListener.class)
public class ClaimStatus {
    @Id
    @Column(nullable = false, updatable = false, columnDefinition = "uuid")
    @GeneratedValue
    private UUID id;

    @Column(name = "claim_id", nullable = false)
    private UUID claimId;

    @Column(nullable = false)
    private String status;

    @Column(name = "agent_comment", nullable = false)
    private String agentComment;

    @Column(name = "agent_email", nullable = false)
    private String agentEmail;

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