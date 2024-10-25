package com.example.claims.mapper;

import com.example.claims.dto.*;
import com.example.claims.model.Claim;
import com.example.claims.model.ClaimStatus;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClaimMapper {

    public Claim toEntity(ClaimRequestDTO dto) {
        Claim claim = new Claim();
        claim.setCompany(dto.getCompany());
        claim.setReason(dto.getReason());
        claim.setDescription(dto.getDescription());
        //claim.setAttachments(dto.getAttachments());
        claim.setCustomerEmail(dto.getEmail());
        return claim;
    }

    public ClaimResponseDTO toResponseDTO(Claim claim) {
        ClaimResponseDTO dto = new ClaimResponseDTO();
        dto.setId(claim.getId());
        dto.setCode(claim.getCode());
        dto.setCompany(claim.getCompany());
        dto.setReason(claim.getReason());
        dto.setDescription(claim.getDescription());
        dto.setAttachments(claim.getAttachments());
        dto.setCustomerEmail(claim.getCustomerEmail());
        dto.setStatus(claim.getStatus());
        dto.setCreationDate(claim.getCreationDate());
        return dto;
    }

    public ClaimListItemDTO toListItemDTO(Claim claim) {
        ClaimListItemDTO dto = new ClaimListItemDTO();
        dto.setId(claim.getId());
        dto.setCode(claim.getCode());
        dto.setCompany(claim.getCompany());
        dto.setReason(claim.getReason());
        dto.setStatus(claim.getStatus());
        dto.setCreationDate(claim.getCreationDate());
        return dto;
    }

    public ClaimDetailDTO toDetailDTO(Claim claim) {
        ClaimDetailDTO dto = new ClaimDetailDTO();
        dto.setId(claim.getId());
        dto.setCode(claim.getCode());
        dto.setCompany(claim.getCompany());
        dto.setReason(claim.getReason());
        dto.setDescription(claim.getDescription());
        dto.setAttachments(claim.getAttachments());
        dto.setCustomerEmail(claim.getCustomerEmail());
        dto.setStatus(claim.getStatus());
        dto.setCreationDate(claim.getCreationDate());
        return dto;
    }

    public StatusHistoryDTO toStatusHistoryDTO(ClaimStatus status) {
        StatusHistoryDTO dto = new StatusHistoryDTO();
        dto.setStatus(status.getStatus());
        dto.setAgentComment(status.getAgentComment());
        dto.setAgentEmail(status.getAgentEmail());
        dto.setCreationDate(status.getCreationDate());
        return dto;
    }
}