package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
import com.digitalsystemdreamer.servicerefdata.request.MembershipDto;
import com.digitalsystemdreamer.servicerefdata.service.MembershipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vi")
@Slf4j
public class MembershipController {
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private Assembler assembler;

    @GetMapping("/memberships")
    public CollectionModel<EntityModel<MembershipDto>> getAllMemberships() {
        List<Membership> all = membershipService.getAllMemberships();
        return assembler.toEntityModelList(all);
    }

    @PostMapping("/memberships")
    public EntityModel<MembershipDto> saveMembership(@RequestBody MembershipDto membershipDto) {
        Membership membership = membershipService.saveMembership(assembler.toEntity(membershipDto));
        log.info("Membership Created with ID: {}", membership.getId());
        return assembler.toEntityModel(membership);
    }

    @GetMapping("/memberships/{id}")
    public EntityModel<MembershipDto> getMembership(@PathVariable Integer id) {
        Membership membership = membershipService.getMembership(id);
        return assembler.toEntityModel(membership);
    }
}
