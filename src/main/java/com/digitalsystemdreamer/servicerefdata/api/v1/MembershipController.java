package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import com.digitalsystemdreamer.servicerefdata.service.MembershipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class MembershipController {
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private Assembler assembler;

    @GetMapping("/memberships")
    public List<MembershipDto> getAllMemberships() {
        return membershipService.getAllMemberships();
    }

    @PostMapping("/memberships")
    public MembershipDto saveMembership(@RequestBody MembershipDto membershipDto) {
        MembershipDto membershipDto1 = membershipService.saveMembership(membershipDto);
        log.info("Membership Created with ID: {}", membershipDto1.getMembershipId());
        return membershipDto1;
    }

    @GetMapping("/memberships/{id}")
    public MembershipDto getMembership(@PathVariable Integer id) {
        return membershipService.getMembership(id);
    }
}
