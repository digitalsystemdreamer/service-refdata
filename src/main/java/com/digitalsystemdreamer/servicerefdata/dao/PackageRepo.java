package com.digitalsystemdreamer.servicerefdata.dao;

import com.digitalsystemdreamer.servicerefdata.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface PackageRepo extends JpaRepository<Package, Integer>, RevisionRepository<Package, Integer, Long> {
}
