package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.dao.MembershipRepo;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipService {
    @Autowired
    private MembershipRepo membershipRepo;
    public List<Membership> getAllMemberships(){
        return membershipRepo.findAll();
    }
    public Membership saveMembership(Membership membership){
        return membershipRepo.save(membership);
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
