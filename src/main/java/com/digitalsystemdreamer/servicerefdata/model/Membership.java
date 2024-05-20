package com.digitalsystemdreamer.servicerefdata.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "membership")
    private List<MembershipFacilityMap> membershipFacilities = new ArrayList<>();
}