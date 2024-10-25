package com.example.claims.controller;

import com.example.claims.dto.ClaimDetailDTO;
import com.example.claims.dto.ClaimListItemDTO;
import com.example.claims.dto.ClaimRequestDTO;
import com.example.claims.dto.ClaimResponseDTO;
import com.example.claims.dto.ClaimStatusRequestDTO;
import com.example.claims.service.ClaimService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/claims")
@RequiredArgsConstructor
@Tag(name = "Claims", description = "Claims management APIs")
@CrossOrigin(origins = "*")
public class ClaimController {
    private final ClaimService claimService;

    @PostMapping
    @Operation(summary = "Create a new claim")
    public ResponseEntity<ClaimResponseDTO> createClaim(@Valid @RequestBody ClaimRequestDTO claimRequest) {
        return new ResponseEntity<>(
                claimService.createClaim(claimRequest),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    @Operation(summary = "Get all claims with their latest status")
    public ResponseEntity<List<ClaimListItemDTO>> getAllClaims() {
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get claim details by ID")
    public ResponseEntity<ClaimDetailDTO> getClaimById(@PathVariable UUID id) {
        return ResponseEntity.ok(claimService.getClaimById(id));
    }

    @PostMapping("/{id}/status")
    @Operation(summary = "Update claim status")
    public ResponseEntity<ClaimResponseDTO> updateClaimStatus(
            @PathVariable UUID id,
            @Valid @RequestBody ClaimStatusRequestDTO statusRequest) {
        return ResponseEntity.ok(
                claimService.updateClaimStatus(id, statusRequest)
        );
    }
}
