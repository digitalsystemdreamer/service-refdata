package com.digitalsystemdreamer.servicerefdata.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class MembershipFacilityMapId implements Serializable {
    private Integer membershipId;
    private Integer facilityId;

    public MembershipFacilityMapId(Integer membershipId, Integer facilityId) {
        this.membershipId = membershipId;
        this.facilityId = facilityId;
    }
}
