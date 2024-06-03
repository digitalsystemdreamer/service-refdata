package com.digitalsystemdreamer.servicerefdata.api.v2;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.service.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v2")
@Slf4j
public class FacilityControllerV2 {
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private Assembler assembler;

    @GetMapping("/facilities")
    public CollectionModel<EntityModel<FacilityDto>> getAllFacilities() {
        List<EntityModel<FacilityDto>> all = facilityService.getAllFacilities().stream().map(facilityDto -> assembler.toEntityModel(facilityDto)).toList();
        return CollectionModel.of(all, linkTo(methodOn(FacilityControllerV2.class).getAllFacilities()).withSelfRel());
    }

    @PostMapping("/facilities")
    public EntityModel<FacilityDto> saveFacility(@RequestBody FacilityDto facilityDto) {
        FacilityDto facilityDto1 = facilityService.saveFacility(facilityDto);
        log.info("Facility Created with ID: {}", facilityDto1.getFacilityId());
        return assembler.toEntityModel(facilityDto1);
    }

    @PutMapping("/facilities/{id}")
    public EntityModel<FacilityDto> updateFacility(@PathVariable Integer id, @RequestBody FacilityDto facilityDto) {
        FacilityDto facilityDto1 = facilityService.updateFacility(id, facilityDto);
        log.info("Facility Updated with ID: {}", id);
        return assembler.toEntityModel(facilityDto1);
    }

    @GetMapping("/facilities/{id}")
    public EntityModel<FacilityDto> getFacility(@PathVariable Integer id) {
        return assembler.toEntityModel(facilityService.getFacility(id));
    }

}
