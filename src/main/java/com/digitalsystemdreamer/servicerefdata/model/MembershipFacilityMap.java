package com.digitalsystemdreamer.servicerefdata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
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
    private Integer hours;
}
