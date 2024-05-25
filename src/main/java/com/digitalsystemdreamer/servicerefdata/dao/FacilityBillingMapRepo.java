package com.digitalsystemdreamer.servicerefdata.dao;

import com.digitalsystemdreamer.servicerefdata.model.FacilityBillingMap;
import com.digitalsystemdreamer.servicerefdata.model.FacilityBillingMapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface FacilityBillingMapRepo  extends JpaRepository<FacilityBillingMap, FacilityBillingMapId>, RevisionRepository<FacilityBillingMap, FacilityBillingMapId, Long> {
}
