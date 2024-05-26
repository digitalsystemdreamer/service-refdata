package com.digitalsystemdreamer.servicerefdata.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MembershipDto {
    private int membershipId;
    private String name;
    private String description;
    private LocalDate validFrom;
    private LocalDate validTo;
    private PackageDto floorPackage;
    private Integer points;
    private List<FacilityDto> facilities;
}
