package com.example.claims.controller;

import com.example.claims.constants.ClaimConstants;
import com.example.claims.dto.MasterDataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/master-data")
@CrossOrigin(origins = "*")
public class MasterDataController {

    @GetMapping
    public ResponseEntity<MasterDataDTO> getMasterData() {
        return ResponseEntity.ok(MasterDataDTO.create());
    }

    @GetMapping("/companies")
    public ResponseEntity<List<String>> getCompanies() {
        return ResponseEntity.ok(ClaimConstants.VALID_COMPANIES);
    }

    @GetMapping("/reasons")
    public ResponseEntity<List<String>> getReasons() {
        return ResponseEntity.ok(ClaimConstants.VALID_REASONS);
    }

    @GetMapping("/statuses")
    public ResponseEntity<List<String>> getStatuses() {
        return ResponseEntity.ok(ClaimConstants.VALID_STATUSES);
    }
}