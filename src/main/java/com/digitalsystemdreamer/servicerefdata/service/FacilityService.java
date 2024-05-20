package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.dao.FacilityRepo;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.producer.FacilityProducer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepo facilityRepo;

    @Autowired
    FacilityProducer facilityProducer;

    public List<Facility> getAllFacilities() {
        return facilityRepo.findAll();
    }

    public Facility saveFacility(Facility facility) {
        facility = facilityRepo.save(facility);
        facilityProducer.sendMessage("{\"name\" : \""+facility.getName()+"\",\"description\" : \""+facility.getDescription()+"\"}");
        return facility;
    }

    public Facility updateFacility(Integer id, Facility facility) {
        if (facilityRepo.existsById(id)) {
            facility.setId(id);
            facility = facilityRepo.save(facility);
        } else {
            throw new EntityNotFoundException();
        }
        return facility;
    }

    public Facility getFacility(Integer id) {
        return facilityRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
