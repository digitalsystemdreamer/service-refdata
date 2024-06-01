package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
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
@RequestMapping("/api/v1")
@Slf4j
public class FacilityController {
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private Assembler assembler;

    @GetMapping("/facilities")
    public CollectionModel<EntityModel<FacilityDto>> getAllFacilities() {
        List<Facility> all = facilityService.getAllFacilities();
        return assembler.toEntityModelFacilityList(all);
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
        Facility facility = facilityService.getFacility(id);
        return assembler.toEntityModel(facility);
    }

    @QueryMapping
    public Facility facilityById(@Argument String id) {
        return facilityService.getFacility(Integer.valueOf(id));
    }

}
