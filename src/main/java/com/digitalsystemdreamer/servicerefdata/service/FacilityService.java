package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dao.BillingRepo;
import com.digitalsystemdreamer.servicerefdata.dao.FacilityRepo;
import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.model.Billing;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.producer.FacilityProducer;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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

    @Autowired
    private Assembler assembler;

    public List<Facility> getAllFacilities() {
        return facilityRepo.findAll();
    }

    public FacilityDto saveFacility(final FacilityDto facilityDto) {
        Facility toBeSaved = modelMapper.map(facilityDto, Facility.class);
        Optional.of(facilityDto.getFacilityBillings()).ifPresent(billingDtos -> billingDtos.forEach(billingDto -> {
            Billing billing = billingRepo.findById(billingDto.getBillingId()).orElseThrow(RuntimeException:: new);
            toBeSaved.addBilling(billing, billingDto.getBillingRate());
        }));
        Facility saved = facilityRepo.save(toBeSaved);
        FacilityDto savedFacilityDto = assembler.toDto(saved);
        facilityProducer.sendMessage(savedFacilityDto);
        return savedFacilityDto;
    }

    public FacilityDto updateFacility(Integer id, FacilityDto facilityDto) {
        if (facilityRepo.existsById(id)) {
            Facility toBeSaved = modelMapper.map(facilityDto, Facility.class);
            toBeSaved.setFacilityId(id);
            Optional.of(facilityDto.getFacilityBillings()).ifPresent(billingDtos -> billingDtos.forEach(billingDto -> {
                Billing billing = billingRepo.findById(billingDto.getBillingId()).orElseThrow(RuntimeException:: new);
                toBeSaved.addBilling(billing, billingDto.getBillingRate());
            }));
            Facility saved = facilityRepo.save(toBeSaved);
            FacilityDto savedFacilityDto = assembler.toDto(saved);
            facilityProducer.sendMessage(savedFacilityDto);
            return savedFacilityDto;
        } else {
            throw new EntityNotFoundException();
        }
    }

    public Facility getFacility(Integer id) {
        return facilityRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
