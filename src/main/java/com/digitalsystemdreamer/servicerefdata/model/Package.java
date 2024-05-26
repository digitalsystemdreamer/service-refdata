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
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer packageId;
    private String packageName;
}
