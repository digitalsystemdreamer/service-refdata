package com.digitalsystemdreamer.servicerefdata.assembler;

import com.digitalsystemdreamer.servicerefdata.api.v2.FacilityControllerV2;
import com.digitalsystemdreamer.servicerefdata.api.v2.MembershipControllerV2;
import com.digitalsystemdreamer.servicerefdata.dto.BillingDto;
import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.dto.MembershipDto;
import com.digitalsystemdreamer.servicerefdata.model.Billing;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.model.Membership;
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
    public MembershipDto toDto(Membership membership) {
        MembershipDto membershipDto = modelMapper.map(membership, MembershipDto.class);
        membershipDto.setFacilities(membership.getMembershipFacilities().stream().map(membershipFacilityMap -> {
            FacilityDto facilityDto = modelMapper.map(membershipFacilityMap.getFacility(), FacilityDto.class);
            facilityDto.setDuration(membershipFacilityMap.getDuration());
            return facilityDto;
        }).collect(Collectors.toList()));
        return membershipDto;
    }

    public EntityModel<MembershipDto> toEntityModel(MembershipDto membershipDto) {
        return EntityModel.of(membershipDto,
                linkTo(methodOn(MembershipControllerV2.class).getMembership(membershipDto.getMembershipId())).withSelfRel(),
                linkTo(methodOn(MembershipControllerV2.class).getAllMemberships()).withRel("memberships"));
    }

    public CollectionModel<EntityModel<MembershipDto>> toEntityModelList(List<Membership> memberships) {
        List<EntityModel<MembershipDto>> membershipDtos = memberships.stream().map((membership) -> toEntityModel(toDto(membership))).toList();
        return CollectionModel.of(membershipDtos, linkTo(methodOn(MembershipControllerV2.class).getAllMemberships()).withSelfRel());
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
                linkTo(methodOn(FacilityControllerV2.class).getFacility(facilityDto.getFacilityId())).withSelfRel(),
                linkTo(methodOn(FacilityControllerV2.class).getAllFacilities()).withRel("facilities"));
    }

    public EntityModel<FacilityDto> toEntityModel(FacilityDto facilityDto){
        return EntityModel.of(facilityDto,
                linkTo(methodOn(FacilityControllerV2.class).getFacility(facilityDto.getFacilityId())).withSelfRel(),
                linkTo(methodOn(FacilityControllerV2.class).getAllFacilities()).withRel("facilities"));
    }

    public FacilityDto toDto(Facility facility){
        FacilityDto facilityDto = modelMapper.map(facility, FacilityDto.class);
        List<BillingDto> facilityBillings = new java.util.ArrayList<>(List.of());
        Optional.of(facility.getFacilityBillingMaps()).ifPresent(facilityBillingMaps -> facilityBillingMaps.forEach(facilityBillingMap -> {
            BillingDto billingDto = modelMapper.map(facilityBillingMap.getBilling(), BillingDto.class);
            billingDto.setBillingRate(facilityBillingMap.getRate());
            facilityBillings.add(billingDto);
        }));
        facilityDto.setFacilityBillings(facilityBillings);
        return facilityDto;
    }

    public CollectionModel<EntityModel<FacilityDto>> toEntityModelFacilityList(List<Facility> facilities) {
        List<EntityModel<FacilityDto>> faEntityModels = facilities.stream().map(this::toEntityModel).toList();
        return CollectionModel.of(faEntityModels, linkTo(methodOn(FacilityControllerV2.class).getAllFacilities()).withSelfRel());
    }

}
