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

import java.util.Collections;
import java.util.List;

@Service
public class MembershipService {
    @Autowired
    private MembershipRepo membershipRepo;
    @Autowired
    private FacilityRepo facilityRepo;
    @Autowired
    private MembershipFacilityMapRepo membershipFacilityMapRepo;

    public List<Membership> getAllMemberships(){
        return membershipRepo.findAll();
    }
    public Membership saveMembership(Membership membership){

        Membership saved = membershipRepo.save(membership);
        if(!CollectionUtils.isEmpty(membership.getMembershipFacilities())){
            List<MembershipFacilityMap> membershipFacilities = membership.getMembershipFacilities();
            membershipFacilities.forEach(membershipFacilityMap -> {
                Facility facility = facilityRepo.findById(membershipFacilityMap.getFacility().getId()).get();
                MembershipFacilityMap map = new MembershipFacilityMap();
                map.setMembership(saved);
                map.setFacility(facility);
                map.setHours(membershipFacilityMap.getHours());
                map.setId(new MembershipFacilityMapId());
                membershipFacilityMapRepo.save(map);
            });
        }
        return membership;
    }

    public Membership updateMembership(Membership membership){
        if(membershipRepo.existsById(membership.getId())){
            membership = membershipRepo.save(membership);
        } else {
            throw new EntityNotFoundException();
        }
        return membership;
    }

    public Membership getMembership(Integer id){
        return membershipRepo.findById(id).orElseThrow(EntityNotFoundException :: new);
    }
}
