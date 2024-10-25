package com.example.claims.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StatusHistoryDTO {
    private String status;
    private String agentComment;
    private String agentEmail;
    private LocalDateTime creationDate;
}
