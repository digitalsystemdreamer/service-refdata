package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.model.Membership;
import com.digitalsystemdreamer.servicerefdata.service.MembershipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/vi")
@Slf4j
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @GetMapping("/memberships")
    public CollectionModel<EntityModel<Membership>> getAllMemberships(){
        List<Membership> all = membershipService.getAllMemberships();
        List<EntityModel<Membership>> allMemberships = all.stream()
                .map(membership -> EntityModel.of(membership,
                        linkTo(methodOn(MembershipController.class).getMembership(membership.getId())).withSelfRel(),
                        linkTo(methodOn(MembershipController.class).getAllMemberships()).withRel("memberships")))
                        .collect(Collectors.toList());
        return CollectionModel.of(allMemberships, linkTo(methodOn(MembershipController.class).getAllMemberships()).withSelfRel());
    }
    @PostMapping("/memberships")
    public EntityModel<Membership> saveMembership(@RequestBody Membership membership){
        Membership membership1 = membershipService.saveMembership(membership);
        log.info("Membership Created with ID: {}", membership1.getId());
        return EntityModel.of(membership1,
                linkTo(methodOn(MembershipController.class).getMembership(membership1.getId())).withSelfRel(),
                linkTo(methodOn(MembershipController.class).getAllMemberships()).withRel("memberships"));
    }

    @GetMapping("/memberships/{id}")
    public EntityModel<Membership> getMembership(@PathVariable Integer id){
        Membership membership = membershipService.getMembership(id);

        return EntityModel.of(membership,
                linkTo(methodOn(MembershipController.class).getMembership(id)).withSelfRel(),
                linkTo(methodOn(MembershipController.class).getAllMemberships()).withRel("memberships"));
    }
}
