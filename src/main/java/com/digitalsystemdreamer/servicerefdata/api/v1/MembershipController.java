package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.model.Membership;
import com.digitalsystemdreamer.servicerefdata.service.MembershipService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi")
@Slf4j
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @GetMapping
    public List<Membership> getAllMemberships(){
        return membershipService.getAllMemberships();
    }
    @PostMapping
    public Membership saveMembership(@RequestBody Membership membership){
        Membership membership1 = membershipService.saveMembership(membership);
        log.info("Membership Created with ID: {}", membership1.id);
        return membership1;
    }
}
