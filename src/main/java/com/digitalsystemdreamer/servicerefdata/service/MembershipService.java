package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.dao.FacilityRepo;
import com.digitalsystemdreamer.servicerefdata.dao.MembershipFacilityMapRepo;
import com.digitalsystemdreamer.servicerefdata.dao.MembershipRepo;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
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

    public List<Membership> getAllMemberships() {
        return membershipRepo.findAll();
    }

    public Membership saveMembership(MembershipDto membershipDto) {
        Membership membership = modelMapper.map(membershipDto, Membership.class);
        membership.getMembershipFacilities().clear();
        if (!CollectionUtils.isEmpty(membershipDto.getFacilities())) {
            membershipDto.getFacilities().forEach(facilityDto -> {
                Facility facility = facilityRepo.findById(facilityDto.getFacilityId()).orElseThrow(RuntimeException:: new);
                membership.addFacility(facility, facilityDto.getDuration());
            });
        }
        return membershipRepo.save(membership);
    }

    public Membership updateMembership(Membership membership) {
        if (membershipRepo.existsById(membership.getMembershipId())) {
            membership = membershipRepo.save(membership);
        } else {
            throw new EntityNotFoundException();
        }
        return membership;
    }

    public Membership getMembership(Integer id) {
        return membershipRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
