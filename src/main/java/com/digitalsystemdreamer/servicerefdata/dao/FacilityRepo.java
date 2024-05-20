package com.digitalsystemdreamer.servicerefdata.dao;

import com.digitalsystemdreamer.servicerefdata.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityRepo extends JpaRepository<Facility, Integer>, RevisionRepository<Facility, Integer, Long> {
}