package com.example.claims.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class ClaimResponseDTO {
    private UUID id;
    private String code;
    private String company;
    private String reason;
    private String description;
    private List<String> attachments;
    private String customerEmail;
    private LocalDateTime creationDate;
    private String status;
    private String updateUser;
}