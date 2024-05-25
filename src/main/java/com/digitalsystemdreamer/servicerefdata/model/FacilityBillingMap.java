package com.digitalsystemdreamer.servicerefdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
@NoArgsConstructor
public class FacilityBillingMap {
    @EmbeddedId
    private FacilityBillingMapId id;

    public FacilityBillingMap(Facility facility, Billing billing) {
        this.billing = billing;
        this.facility = facility;
        this.id = new FacilityBillingMapId(facility.getFacilityId(), billing.getBillingId());
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("facilityId")
    private Facility facility;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("billingId")
    private Billing billing;
    private Integer rate;

}
