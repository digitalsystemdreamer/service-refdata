package com.digitalsystemdreamer.servicerefdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
@NoArgsConstructor
public class MembershipFacilityMap {
    @EmbeddedId
    private MembershipFacilityMapId id;

    @ManyToOne
    @MapsId("membershipId")
    @JsonIgnore
    private Membership membership;

    @ManyToOne
    @MapsId("facilityId")
    private Facility facility;
    private Integer duration;

    public MembershipFacilityMap(Membership membership, Facility facility) {
        this.membership = membership;
        this.facility = facility;
        this.id = new MembershipFacilityMapId(membership.getMembershipId(), facility.getFacilityId());
    }
}
