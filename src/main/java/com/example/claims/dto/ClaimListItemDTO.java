package com.example.claims.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ClaimListItemDTO {
    private UUID id;
    private String code;
    private String company;
    private String reason;
    private String status;
    private LocalDateTime creationDate;
}
