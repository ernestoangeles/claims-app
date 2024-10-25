package com.example.claims.service;

import com.example.claims.dto.ClaimDetailDTO;
import com.example.claims.dto.ClaimListItemDTO;
import com.example.claims.dto.ClaimRequestDTO;
import com.example.claims.dto.ClaimResponseDTO;
import com.example.claims.dto.ClaimStatusRequestDTO;

import java.util.List;
import java.util.UUID;

public interface ClaimService {
    ClaimResponseDTO createClaim(ClaimRequestDTO claimRequest);
    List<ClaimListItemDTO> getAllClaims();
    ClaimDetailDTO getClaimById(UUID id);
    ClaimResponseDTO updateClaimStatus(UUID id, ClaimStatusRequestDTO statusRequest);
}
