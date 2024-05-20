package com.digitalsystemdreamer.servicerefdata.dto;

import lombok.Data;

import java.util.List;

@Data
public class MembershipDto {
    private int id;
    private String name;
    private String description;
    private List<FacilityDto> facilities;
}
