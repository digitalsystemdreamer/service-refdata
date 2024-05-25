package com.digitalsystemdreamer.servicerefdata.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FacilityDto {
    private Integer facilityId;
    private String name;
    private String description;
    private LocalDate availabilityDate;
    private List<BillingDto> facilityBillings;
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate updatedDate;
    private String updatedBy;
    private Integer duration;
}
