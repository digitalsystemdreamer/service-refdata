package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.dao.FacilityRepo;
import com.digitalsystemdreamer.servicerefdata.dao.MembershipFacilityMapRepo;
import com.digitalsystemdreamer.servicerefdata.dao.MembershipRepo;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
import com.digitalsystemdreamer.servicerefdata.model.MembershipFacilityMap;
import com.digitalsystemdreamer.servicerefdata.model.MembershipFacilityMapId;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Membership> getAllMemberships() {
        return membershipRepo.findAll();
    }

    public Membership saveMembership(Membership membership) {

        Membership saved = membershipRepo.save(membership);
        if (!CollectionUtils.isEmpty(membership.getMembershipFacilities())) {
            List<MembershipFacilityMap> membershipFacilities = membership.getMembershipFacilities();
            List<MembershipFacilityMap> collect = membershipFacilities.stream().map(membershipFacilityMap -> {
                Facility facility = facilityRepo.findById(membershipFacilityMap.getFacility().getFacilityId()).get();
                MembershipFacilityMap map = new MembershipFacilityMap();
                map.setMembership(saved);
                map.setFacility(facility);
                map.setDuration(membershipFacilityMap.getDuration());
                map.setId(new MembershipFacilityMapId());
                membershipFacilityMapRepo.save(map);
                return map;
            }).toList();
            membership.setMembershipFacilities(collect);
        }
        return membership;
    }

    public Membership updateMembership(Membership membership) {
        if (membershipRepo.existsById(membership.getId())) {
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
