package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.dao.FacilityRepo;
import com.digitalsystemdreamer.servicerefdata.dao.MembershipRepo;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepo facilityRepo;
    public List<Facility> getAllFacilities(){
        return facilityRepo.findAll();
    }
    public Facility saveFacility(Facility facility){
        return facilityRepo.save(facility);
    }

    public Facility updateFacility(Facility facility){
        if(facilityRepo.existsById(facility.getId())){
            facility = facilityRepo.save(facility);
        } else {
            throw new EntityNotFoundException();
        }
        return facility;
    }

    public Facility getFacility(Integer id){
        return facilityRepo.findById(id).orElseThrow(EntityNotFoundException :: new);
    }
}
