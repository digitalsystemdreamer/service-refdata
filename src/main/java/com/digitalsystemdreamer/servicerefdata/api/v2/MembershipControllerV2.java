package com.digitalsystemdreamer.servicerefdata.api.v2;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import com.digitalsystemdreamer.servicerefdata.service.MembershipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2")
@Slf4j
public class MembershipControllerV2 {
    @Autowired
    private MembershipService membershipService;
    @Autowired
    private Assembler assembler;

    @GetMapping("/memberships")
    public CollectionModel<EntityModel<MembershipDto>> getAllMemberships() {
        List<EntityModel<MembershipDto>> entityModelList = membershipService.getAllMemberships().stream().map(membershipDto -> assembler.toEntityModel(membershipDto)).toList();
        return CollectionModel.of(entityModelList, linkTo(methodOn(MembershipControllerV2.class).getAllMemberships()).withSelfRel());
    }

    @PostMapping("/memberships")
    public EntityModel<MembershipDto> saveMembership(@RequestBody MembershipDto membershipDto) {
        MembershipDto membershipDto1 = membershipService.saveMembership(membershipDto);
        log.info("Membership Created with ID: {}", membershipDto1.getMembershipId());
        return assembler.toEntityModel(membershipDto1);
    }

    @GetMapping("/memberships/{id}")
    public EntityModel<MembershipDto> getMembership(@PathVariable Integer id) {
        MembershipDto membershipDto = membershipService.getMembership(id);
        return assembler.toEntityModel(membershipDto);
    }
}
