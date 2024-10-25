package com.example.claims.controller;

import com.example.claims.dto.*;
import com.example.claims.service.ClaimService;
import com.example.claims.util.TestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClaimController.class)
class ClaimControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClaimService claimService;

    @Test
    void createClaim_Success() throws Exception {
        // Arrange
        var request = TestDataBuilder.buildClaimRequestDTO();
        var response = new ClaimResponseDTO();
        when(claimService.createClaim(any())).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/claims")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getAllClaims_Success() throws Exception {
        // Arrange
        var claimListItem = new ClaimListItemDTO();
        when(claimService.getAllClaims()).thenReturn(Arrays.asList(claimListItem));

        // Act & Assert
        mockMvc.perform(get("/claims"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void getClaimById_Success() throws Exception {
        // Arrange
        UUID claimId = UUID.randomUUID();
        var claimDetail = new ClaimDetailDTO();
        when(claimService.getClaimById(claimId)).thenReturn(claimDetail);

        // Act & Assert
        mockMvc.perform(get("/claims/{id}", claimId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void updateClaimStatus_Success() throws Exception {
        // Arrange
        UUID claimId = UUID.randomUUID();
        var request = TestDataBuilder.buildClaimStatusRequestDTO();
        var response = new ClaimResponseDTO();
        when(claimService.updateClaimStatus(eq(claimId), any())).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/claims/{id}/status", claimId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
