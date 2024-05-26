package com.digitalsystemdreamer.servicerefdata.model;

import com.digitalsystemdreamer.servicerefdata.dto.PackageDto;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Audited
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int membershipId;
    private String name;
    private String description;
    private LocalDate validFrom;
    private LocalDate validTo;
    @ManyToOne
    private Package floorPackage;
    private Integer points;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MembershipFacilityMap> membershipFacilities = new ArrayList<>();
    public void addFacility(Facility facility, Integer duration){
        MembershipFacilityMap membershipFacilityMap = new MembershipFacilityMap(this, facility);
        membershipFacilityMap.setDuration(duration);
        membershipFacilities.add(membershipFacilityMap);
    }
    @CreationTimestamp
    private LocalDate createdDate;
    private String createdBy;
    @UpdateTimestamp
    private LocalDate updatedDate;
    private String updatedBy;
}