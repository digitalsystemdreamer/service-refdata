package com.digitalsystemdreamer.servicerefdata.dao;

import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.model.MembershipFacilityMap;
import com.digitalsystemdreamer.servicerefdata.model.MembershipFacilityMapId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipFacilityMapRepo extends JpaRepository<MembershipFacilityMap, MembershipFacilityMapId> , RevisionRepository<MembershipFacilityMap, MembershipFacilityMapId, Long> {
}