package com.digitalsystemdreamer.servicerefdata.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MembershipFacilityMap {
    @EmbeddedId
    private MembershipFacilityMapId id;

    @ManyToOne
    @MapsId("membershipId")
    private Membership membership;

    @ManyToOne
    @MapsId("facilityId")
    private Facility facility;
    private Integer hours;
}
