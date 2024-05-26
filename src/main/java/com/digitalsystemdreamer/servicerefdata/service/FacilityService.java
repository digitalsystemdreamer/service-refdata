package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.dao.BillingRepo;
import com.digitalsystemdreamer.servicerefdata.dao.FacilityRepo;
import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.model.Billing;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.producer.FacilityProducer;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepo facilityRepo;
    @Autowired
    private BillingRepo billingRepo;

    @Autowired
    FacilityProducer facilityProducer;

    @Autowired
    private ModelMapper modelMapper;

    public List<Facility> getAllFacilities() {
        return facilityRepo.findAll();
    }

    public Facility saveFacility(final FacilityDto facilityDto) {
        Facility facility = modelMapper.map(facilityDto, Facility.class);
        Optional.of(facilityDto.getFacilityBillings()).ifPresent(billingDtos -> billingDtos.forEach(billingDto -> {
            Billing billing = billingRepo.findById(billingDto.getBillingId()).orElseThrow(RuntimeException:: new);
            facility.addBilling(billing, billingDto.getBillingRate());
        }));
        Facility saved = facilityRepo.save(facility);
        facilityProducer.sendMessage("{\"name\" : \""+facility.getName()+"\",\"description\" : \""+facility.getDescription()+"\"}");
        return saved;
    }

    public Facility updateFacility(Integer id, FacilityDto facilityDto) {
        Facility saved = null;
        if (facilityRepo.existsById(id)) {
            Facility facility = modelMapper.map(facilityDto, Facility.class);
            facility.setFacilityId(id);
            Optional.of(facilityDto.getFacilityBillings()).ifPresent(billingDtos -> billingDtos.forEach(billingDto -> {
                Billing billing = billingRepo.findById(billingDto.getBillingId()).orElseThrow(RuntimeException:: new);
                facility.addBilling(billing, billingDto.getBillingRate());
            }));
            saved = facilityRepo.save(facility);
            facilityProducer.sendMessage("{\"name\" : \""+saved.getName()+"\",\"description\" : \""+saved.getDescription()+"\"}");
        } else {
            throw new EntityNotFoundException();
        }
        return saved;
    }

    public Facility getFacility(Integer id) {
        return facilityRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
