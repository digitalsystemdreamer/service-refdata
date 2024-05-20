package com.digitalsystemdreamer.servicerefdata.dao;

import com.digitalsystemdreamer.servicerefdata.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepo extends JpaRepository<Membership, Integer>, RevisionRepository<Membership, Integer, Long> {
}