package com.example.claims.service;

import com.example.claims.dto.ClaimDetailDTO;
import com.example.claims.dto.ClaimListItemDTO;
import com.example.claims.dto.ClaimResponseDTO;
import com.example.claims.exception.ResourceNotFoundException;
import com.example.claims.mapper.ClaimMapper;
import com.example.claims.model.Claim;
import com.example.claims.model.ClaimStatus;
import com.example.claims.repository.ClaimRepository;
import com.example.claims.repository.ClaimStatusRepository;
import com.example.claims.service.impl.ClaimServiceImpl;
import com.example.claims.service.util.ClaimCodeService;
import com.example.claims.util.TestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.example.claims.constants.ClaimConstants.STATUS_IN_REVIEW;
import static com.example.claims.constants.ClaimConstants.STATUS_PENDING;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClaimServiceTest {

    @Mock
    private ClaimRepository claimRepository;

    @Mock
    private ClaimStatusRepository statusRepository;

    @Mock
    private ClaimMapper claimMapper;

    @Mock
    private ClaimCodeService claimCodeService;

    private ClaimService claimService;

    @BeforeEach
    void setUp() {
        claimService = new ClaimServiceImpl(claimRepository, statusRepository, claimMapper, claimCodeService);
    }

    @Test
    void createClaim_Success() {
        // Arrange
        var claimRequest = TestDataBuilder.buildClaimRequestDTO();
        var claim = TestDataBuilder.buildClaim();
        var claimStatus = TestDataBuilder.buildClaimStatus(claim, STATUS_PENDING);
        var expectedResponse = new ClaimResponseDTO();
        String expectedCode = "REC000001";

        when(claimMapper.toEntity(claimRequest)).thenReturn(claim);
        when(claimCodeService.generateCode()).thenReturn(expectedCode);
        when(claimRepository.save(any(Claim.class))).thenAnswer(invocation -> {
            Claim savedClaim = invocation.getArgument(0);
            assertEquals(expectedCode, savedClaim.getCode(), "El código debe ser establecido antes de guardar");
            return savedClaim;
        });
        when(statusRepository.save(any(ClaimStatus.class))).thenReturn(claimStatus);
        when(claimMapper.toResponseDTO(claim)).thenReturn(expectedResponse);

        // Act
        var result = claimService.createClaim(claimRequest);

        // Assert
        assertNotNull(result);
        verify(claimCodeService).generateCode();
        verify(claimRepository).save(any(Claim.class));
        verify(statusRepository).save(any(ClaimStatus.class));
    }

    @Test
    void createMultipleClaims_GeneratesUniqueCodesForEach() {
        // Arrange
        var claimRequest1 = TestDataBuilder.buildClaimRequestDTO();
        var claimRequest2 = TestDataBuilder.buildClaimRequestDTO();
        var claim1 = TestDataBuilder.buildClaim();
        var claim2 = TestDataBuilder.buildClaim();

        when(claimMapper.toEntity(any())).thenReturn(claim1, claim2);
        when(claimCodeService.generateCode())
                .thenReturn("REC000001")
                .thenReturn("REC000002");
        when(claimRepository.save(any(Claim.class))).thenAnswer(i -> i.getArgument(0));
        when(statusRepository.save(any(ClaimStatus.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        claimService.createClaim(claimRequest1);
        claimService.createClaim(claimRequest2);

        // Assert
        verify(claimCodeService, times(2)).generateCode();
        verify(claimRepository, times(2)).save(any(Claim.class));
    }

    @Test
    void getAllClaims_ReturnsListOfClaims() {
        // Arrange
        var claim = TestDataBuilder.buildClaim();
        var claimListItemDTO = new ClaimListItemDTO();

        when(claimRepository.findAllSorted()).thenReturn(Arrays.asList(claim));
        when(claimMapper.toListItemDTO(claim)).thenReturn(claimListItemDTO);

        // Act
        List<ClaimListItemDTO> result = claimService.getAllClaims();

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(claimRepository).findAllSorted();
    }

    @Test
    void getClaimById_WhenExists_ReturnsClaim() {
        // Arrange
        UUID claimId = UUID.randomUUID();
        var claim = TestDataBuilder.buildClaim();
        claim.setId(claimId);
        claim.setCode("REC000001");
        var expectedDTO = new ClaimDetailDTO();

        when(claimRepository.findByIdWithStatusHistory(claimId)).thenReturn(Optional.of(claim));
        when(claimMapper.toDetailDTO(claim)).thenReturn(expectedDTO);

        // Act
        var result = claimService.getClaimById(claimId);

        // Assert
        assertNotNull(result);
        verify(claimRepository).findByIdWithStatusHistory(claimId);
    }

    @Test
    void getClaimById_WhenNotExists_ThrowsException() {
        // Arrange
        UUID claimId = UUID.randomUUID();
        when(claimRepository.findByIdWithStatusHistory(claimId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            claimService.getClaimById(claimId);
        });
    }

    @Test
    void updateClaimStatus_Success() {
        // Arrange
        UUID claimId = UUID.randomUUID();
        var statusRequest = TestDataBuilder.buildClaimStatusRequestDTO();
        var claim = TestDataBuilder.buildClaim();
        claim.setId(claimId);
        claim.setCode("REC000001");
        var claimStatus = TestDataBuilder.buildClaimStatus(claim, STATUS_IN_REVIEW);
        var expectedResponse = new ClaimResponseDTO();

        when(claimRepository.findById(claimId)).thenReturn(Optional.of(claim));
        when(statusRepository.save(any(ClaimStatus.class))).thenReturn(claimStatus);
        when(claimMapper.toResponseDTO(claim)).thenReturn(expectedResponse);

        // Act
        var result = claimService.updateClaimStatus(claimId, statusRequest);

        // Assert
        assertNotNull(result);
        verify(claimRepository).findById(claimId);
        verify(statusRepository).save(any(ClaimStatus.class));
    }

    @Test
    void updateClaimStatus_WhenClaimNotExists_ThrowsException() {
        // Arrange
        UUID claimId = UUID.randomUUID();
        var statusRequest = TestDataBuilder.buildClaimStatusRequestDTO();
        when(claimRepository.findById(claimId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            claimService.updateClaimStatus(claimId, statusRequest);
        });
    }
}