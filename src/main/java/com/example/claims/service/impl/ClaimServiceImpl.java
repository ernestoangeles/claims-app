package com.example.claims.service.impl;

import com.example.claims.dto.ClaimDetailDTO;
import com.example.claims.dto.ClaimListItemDTO;
import com.example.claims.dto.ClaimRequestDTO;
import com.example.claims.dto.ClaimResponseDTO;
import com.example.claims.dto.ClaimStatusRequestDTO;
import com.example.claims.exception.ResourceNotFoundException;
import com.example.claims.mapper.ClaimMapper;
import com.example.claims.model.Claim;
import com.example.claims.model.ClaimStatus;
import com.example.claims.repository.ClaimRepository;
import com.example.claims.repository.ClaimStatusRepository;
import com.example.claims.service.ClaimService;
import com.example.claims.service.util.ClaimCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.claims.constants.ClaimConstants.STATUS_PENDING;

@Service
@RequiredArgsConstructor
@Transactional
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;
    private final ClaimStatusRepository claimStatusRepository;
    private final ClaimMapper claimMapper;
    private final ClaimCodeService claimCodeService;

    @Override
    public ClaimResponseDTO createClaim(ClaimRequestDTO claimRequest) {
        Claim claim = claimMapper.toEntity(claimRequest);
        claim.setCode(claimCodeService.generateCode());
        claim.setStatus(STATUS_PENDING);

        claim.setCreationDate(LocalDateTime.now());
        claim.setCreationUser("system.admin");
        claim.setUpdateDate(LocalDateTime.now());
        claim.setUpdateUser("system.admin");
        claim = claimRepository.save(claim);

        // Create initial status
        ClaimStatus initialStatus = new ClaimStatus();
        initialStatus.setClaimId(claim.getId());
        initialStatus.setStatus(STATUS_PENDING);
        initialStatus.setAgentComment("Claim created");
        initialStatus.setAgentEmail("system@example.com");
        initialStatus.setCreationDate(LocalDateTime.now());
        initialStatus.setCreationUser("system.admin");
        claimStatusRepository.save(initialStatus);

        return claimMapper.toResponseDTO(claim);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClaimListItemDTO> getAllClaims() {
        return claimRepository.findAllSorted()
                .stream()
                .map(claimMapper::toListItemDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClaimDetailDTO getClaimById(UUID id) {
        Claim claim = claimRepository.findByIdWithStatusHistory(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found with id: " + id));

        return claimMapper.toDetailDTO(claim);
    }

    @Override
    public ClaimResponseDTO updateClaimStatus(UUID id, ClaimStatusRequestDTO statusRequest) {
        Claim claim = claimRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found with id: " + id));

        ClaimStatus newStatus = new ClaimStatus();
        newStatus.setClaimId(id);
        newStatus.setStatus(statusRequest.getStatus());
        newStatus.setAgentComment(statusRequest.getAgentComment());
        newStatus.setAgentEmail(statusRequest.getAgentEmail());
        newStatus.setCreationDate(LocalDateTime.now());
        newStatus.setCreationUser("system.admin");
        newStatus.setUpdateDate(LocalDateTime.now());
        newStatus.setUpdateUser("system.admin");
        claimStatusRepository.save(newStatus);

        claim.setStatus(statusRequest.getStatus());
        claim.setUpdateDate(LocalDateTime.now());
        claim.setUpdateUser("system.admin");
        claimRepository.save(claim);

        return claimMapper.toResponseDTO(claim);
    }
}
