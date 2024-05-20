package com.digitalsystemdreamer.servicerefdata.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class MembershipFacilityMapId implements Serializable {
    private Integer membershipId;
    private Integer facilityId;
}
