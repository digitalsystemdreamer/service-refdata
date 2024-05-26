package com.digitalsystemdreamer.servicerefdata.assembler;

import com.digitalsystemdreamer.servicerefdata.api.v1.FacilityController;
import com.digitalsystemdreamer.servicerefdata.api.v1.MembershipController;
import com.digitalsystemdreamer.servicerefdata.dto.BillingDto;
import com.digitalsystemdreamer.servicerefdata.model.Billing;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
import com.digitalsystemdreamer.servicerefdata.model.MembershipFacilityMap;
import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class Assembler {
    @Autowired
    private ModelMapper modelMapper;
    public Membership toEntity(MembershipDto membershipDto) {
        Membership membership = new Membership();
        membership.setId(membershipDto.getId());
        membership.setName(membershipDto.getName());
        membership.setDescription(membershipDto.getDescription());
        membership.setMembershipFacilities(membershipDto.getFacilities().stream().map(facilityDto -> {
            Facility facility = modelMapper.map(facilityDto, Facility.class);
            MembershipFacilityMap membershipFacilityMap = new MembershipFacilityMap();
            membershipFacilityMap.setFacility(facility);
            membershipFacilityMap.setDuration(facilityDto.getDuration());
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
            FacilityDto facilityDto = modelMapper.map(membershipFacilityMap.getFacility(), FacilityDto.class);
            facilityDto.setDuration(membershipFacilityMap.getDuration());
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

    public Facility toEntity(FacilityDto facilityDto){
        Facility facility = modelMapper.map(facilityDto, Facility.class);
        Optional.of(facilityDto.getFacilityBillings()).ifPresent(billingDtos -> billingDtos.forEach(billingDto -> {
            Billing billing = modelMapper.map(billingDto, Billing.class);
            facility.addBilling(billing, billingDto.getBillingRate());
        }));
        return facility;
    }

    public EntityModel<FacilityDto> toEntityModel(Facility facility){
        FacilityDto facilityDto = modelMapper.map(facility, FacilityDto.class);
        List<BillingDto> facilityBillings = new java.util.ArrayList<>(List.of());
        Optional.of(facility.getFacilityBillingMaps()).ifPresent(facilityBillingMaps -> facilityBillingMaps.forEach(facilityBillingMap -> {
            BillingDto billingDto = modelMapper.map(facilityBillingMap.getBilling(), BillingDto.class);
            billingDto.setBillingRate(facilityBillingMap.getRate());
            facilityBillings.add(billingDto);
        }));
        facilityDto.setFacilityBillings(facilityBillings);
        return EntityModel.of(facilityDto,
                linkTo(methodOn(FacilityController.class).getFacility(facilityDto.getFacilityId())).withSelfRel(),
                linkTo(methodOn(FacilityController.class).getAllFacilities()).withRel("facilities"));
    }

    public CollectionModel<EntityModel<FacilityDto>> toEntityModelFacilityList(List<Facility> facilities) {
        List<EntityModel<FacilityDto>> faEntityModels = facilities.stream().map(this::toEntityModel).toList();
        return CollectionModel.of(faEntityModels, linkTo(methodOn(FacilityController.class).getAllFacilities()).withSelfRel());
    }

}
