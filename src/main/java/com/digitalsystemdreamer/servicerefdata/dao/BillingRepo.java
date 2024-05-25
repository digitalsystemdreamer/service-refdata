package com.digitalsystemdreamer.servicerefdata.dao;

import com.digitalsystemdreamer.servicerefdata.model.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface BillingRepo  extends JpaRepository<Billing, Integer>, RevisionRepository<Billing, Integer, Long> {
}
