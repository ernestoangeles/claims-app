package com.example.claims.util;

import com.example.claims.dto.ClaimRequestDTO;
import com.example.claims.dto.ClaimStatusRequestDTO;
import com.example.claims.model.Claim;
import com.example.claims.model.ClaimStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static com.example.claims.constants.ClaimConstants.*;

public class TestDataBuilder {

    public static ClaimRequestDTO buildClaimRequestDTO() {
        ClaimRequestDTO dto = new ClaimRequestDTO();
        dto.setCompany(COMPANY_PROMART);
        dto.setReason(REASON_DEFECTIVE_PRODUCT);
        dto.setDescription("Test description");
        dto.setEmail("customer@test.com");
        //dto.setAttachments(Arrays.asList("file1.jpg", "file2.pdf"));
        return dto;
    }

    public static Claim buildClaim() {
        Claim claim = new Claim();
        claim.setId(UUID.randomUUID());
        claim.setCode("REC000001");
        claim.setCompany(COMPANY_PROMART);
        claim.setReason(REASON_DEFECTIVE_PRODUCT);
        claim.setDescription("Test description");
        claim.setCustomerEmail("customer@test.com");
        claim.setAttachments(Arrays.asList("file1.jpg", "file2.pdf"));
        claim.setStatus("Pending");
        claim.setCreationDate(LocalDateTime.now());
        return claim;
    }

    public static ClaimStatus buildClaimStatus(Claim claim, String status) {
        ClaimStatus claimStatus = new ClaimStatus();
        claimStatus.setId(UUID.randomUUID());
        claimStatus.setStatus(status);
        claimStatus.setAgentComment("Test comment");
        claimStatus.setAgentEmail("agent@test.com");
        claimStatus.setCreationDate(LocalDateTime.now());
        return claimStatus;
    }

    public static ClaimStatusRequestDTO buildClaimStatusRequestDTO() {
        ClaimStatusRequestDTO dto = new ClaimStatusRequestDTO();
        dto.setStatus(STATUS_IN_REVIEW);
        dto.setAgentComment("Test status update");
        dto.setAgentEmail("agent@test.com");
        return dto;
    }
}