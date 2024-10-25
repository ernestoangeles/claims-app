package com.example.claims.dto;

import com.example.claims.constants.ClaimConstants;
import lombok.Data;

import java.util.List;

@Data
public class MasterDataDTO {
    private List<String> companies;
    private List<String> reasons;
    private List<String> statuses;

    public static MasterDataDTO create() {
        MasterDataDTO dto = new MasterDataDTO();
        dto.setCompanies(ClaimConstants.VALID_COMPANIES);
        dto.setReasons(ClaimConstants.VALID_REASONS);
        dto.setStatuses(ClaimConstants.VALID_STATUSES);
        return dto;
    }
}