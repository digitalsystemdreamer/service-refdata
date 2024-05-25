package com.digitalsystemdreamer.servicerefdata.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Data
@Audited
public class Billing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer billingId;
    private String name;
}
