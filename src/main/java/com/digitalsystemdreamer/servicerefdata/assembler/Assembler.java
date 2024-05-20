package com.digitalsystemdreamer.servicerefdata.assembler;

import com.digitalsystemdreamer.servicerefdata.api.v1.MembershipController;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
import com.digitalsystemdreamer.servicerefdata.model.MembershipFacilityMap;
import com.digitalsystemdreamer.servicerefdata.request.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.request.MembershipDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class Assembler {
    public Membership toEntity(MembershipDto membershipDto) {
        Membership membership = new Membership();
        membership.setId(membershipDto.getId());
        membership.setName(membershipDto.getName());
        membership.setDescription(membershipDto.getDescription());
        membership.setMembershipFacilities(membershipDto.getFacilities().stream().map(facilityDto -> {
            Facility facility = new Facility();
            facility.setId(facilityDto.getId());
            facility.setName(facilityDto.getName());
            facility.setDescription(facilityDto.getDescription());
            MembershipFacilityMap membershipFacilityMap = new MembershipFacilityMap();
            membershipFacilityMap.setFacility(facility);
            membershipFacilityMap.setHours(facilityDto.getHours());
            return membershipFacilityMap;
        }).collect(Collectors.toList()));
        return membership;
    }

    public EntityModel<MembershipDto> toEntityModel(Membership membership) {
        MembershipDto membershipDto = new MembershipDto();
        membershipDto.setId(membership.getId());
        membershipDto.setName(membership.getName());
        membershipDto.setDescription(membership.getDescription());
        membershipDto.setFacilities(membership.getMembershipFacilities().stream().map(membershipFacilityMap -> {
            FacilityDto facilityDto = new FacilityDto();
            facilityDto.setId(membershipFacilityMap.getFacility().getId());
            facilityDto.setName(membershipFacilityMap.getFacility().getName());
            facilityDto.setDescription(membershipFacilityMap.getFacility().getDescription());
            facilityDto.setHours(membershipFacilityMap.getHours());
            return facilityDto;
        }).collect(Collectors.toList()));
        return EntityModel.of(membershipDto,
                linkTo(methodOn(MembershipController.class).getMembership(membership.getId())).withSelfRel(),
                linkTo(methodOn(MembershipController.class).getAllMemberships()).withRel("memberships"));
    }

    public CollectionModel<EntityModel<MembershipDto>> toEntityModelList(List<Membership> memberships) {
        List<EntityModel<MembershipDto>> membershipDtos = memberships.stream().map(this::toEntityModel).toList();
        return CollectionModel.of(membershipDtos, linkTo(methodOn(MembershipController.class).getAllMemberships()).withSelfRel());
    }

}
