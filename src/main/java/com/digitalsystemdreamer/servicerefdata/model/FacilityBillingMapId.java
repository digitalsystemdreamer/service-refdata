package com.digitalsystemdreamer.servicerefdata.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Data
public class FacilityBillingMapId implements Serializable {
    private Integer facilityId;
    private Integer billingId;

    public FacilityBillingMapId(Integer facilityId, Integer billingId) {
        this.facilityId = facilityId;
        this.billingId = billingId;
    }
}
