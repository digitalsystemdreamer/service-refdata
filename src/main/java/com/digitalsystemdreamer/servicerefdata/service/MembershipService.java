package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dao.FacilityRepo;
import com.digitalsystemdreamer.servicerefdata.dao.MembershipFacilityMapRepo;
import com.digitalsystemdreamer.servicerefdata.dao.MembershipRepo;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
import com.digitalsystemdreamer.servicerefdata.producer.MembershipProducer;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class MembershipService {
    @Autowired
    private MembershipRepo membershipRepo;
    @Autowired
    private FacilityRepo facilityRepo;
    @Autowired
    private MembershipFacilityMapRepo membershipFacilityMapRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private Assembler assembler;

    @Autowired
    MembershipProducer membershipProducer;

    public List<Membership> getAllMemberships() {
        return membershipRepo.findAll();
    }

    public MembershipDto saveMembership(MembershipDto membershipDto) {
        Membership membership = modelMapper.map(membershipDto, Membership.class);
        membership.getMembershipFacilities().clear();
        if (!CollectionUtils.isEmpty(membershipDto.getFacilities())) {
            membershipDto.getFacilities().forEach(facilityDto -> {
                Facility facility = facilityRepo.findById(facilityDto.getFacilityId()).orElseThrow(RuntimeException:: new);
                membership.addFacility(facility, facilityDto.getDuration());
            });
        }
        MembershipDto assemblerDto = assembler.toDto(membershipRepo.save(membership));
        membershipProducer.sendMessage(assemblerDto);
        return assemblerDto;
    }

    public MembershipDto updateMembership(MembershipDto membershipDto) {
        Membership membership = modelMapper.map(membershipDto, Membership.class);
        membership.getMembershipFacilities().clear();
        if (!CollectionUtils.isEmpty(membershipDto.getFacilities())) {
            membershipDto.getFacilities().forEach(facilityDto -> {
                Facility facility = facilityRepo.findById(facilityDto.getFacilityId()).orElseThrow(RuntimeException:: new);
                membership.addFacility(facility, facilityDto.getDuration());
            });
        }
        return assembler.toDto(membershipRepo.save(membership));
    }

    public MembershipDto getMembership(Integer id) {
        return assembler.toDto(membershipRepo.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
