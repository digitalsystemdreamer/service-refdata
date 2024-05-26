package com.digitalsystemdreamer.servicerefdata.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Audited
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembershipFacilityMap> membershipFacilities = new ArrayList<>();
    public void addFacility(Facility facility, Integer duration){
        MembershipFacilityMap membershipFacilityMap = new MembershipFacilityMap(this, facility);
        membershipFacilityMap.setDuration(duration);
        membershipFacilities.add(membershipFacilityMap);
    }
}