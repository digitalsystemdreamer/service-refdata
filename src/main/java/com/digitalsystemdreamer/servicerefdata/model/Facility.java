package com.digitalsystemdreamer.servicerefdata.model;

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
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer facilityId;
    private String name;
    private String description;
    private LocalDate availabilityDate;
    private Integer rate;
    @OneToMany(
            mappedBy = "facility",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FacilityBillingMap> facilityBillingMaps = new ArrayList<>();

    public void addBilling(Billing billing, Integer billingRate) {
        FacilityBillingMap facilityBillingMap = new FacilityBillingMap(this, billing);
        facilityBillingMap.setRate(billingRate);
        facilityBillingMaps.add(facilityBillingMap);
    }

    @CreationTimestamp
    private LocalDate createdDate;
    private String createdBy;
    @UpdateTimestamp
    private LocalDate updatedDate;
    private String updatedBy;

}
