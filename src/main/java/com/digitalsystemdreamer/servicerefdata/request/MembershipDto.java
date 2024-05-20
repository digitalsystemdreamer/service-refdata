package com.digitalsystemdreamer.servicerefdata.request;

import lombok.Data;

import java.util.List;

@Data
public class MembershipDto {
    private int id;
    private String name;
    private String description;
    private List<FacilityDto> facilities;
}
