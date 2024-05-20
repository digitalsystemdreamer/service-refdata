package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.service.FacilityService;
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
@RequestMapping("/api/v1")
@Slf4j
public class FacilityController {
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private Assembler assembler;

    @GetMapping("/facilities")
    public CollectionModel<EntityModel<Facility>> getAllFacilities() {
        List<Facility> all = facilityService.getAllFacilities();
        List<EntityModel<Facility>> allFacilities = all.stream()
                .map(facility -> EntityModel.of(facility,
                        linkTo(methodOn(FacilityController.class).getFacility(facility.getId())).withSelfRel(),
                        linkTo(methodOn(FacilityController.class).getAllFacilities()).withRel("facilities")))
                .collect(Collectors.toList());
        return CollectionModel.of(allFacilities, linkTo(methodOn(FacilityController.class).getAllFacilities()).withSelfRel());
    }

    @PostMapping("/facilities")
    public EntityModel<Facility> saveFacility(@RequestBody Facility facility) {
        Facility facility1 = facilityService.saveFacility(facility);
        log.info("Facility Created with ID: {}", facility1.getId());
        return EntityModel.of(facility1,
                linkTo(methodOn(FacilityController.class).getFacility(facility1.getId())).withSelfRel(),
                linkTo(methodOn(FacilityController.class).getAllFacilities()).withRel("facilities"));
    }

    @PutMapping("/facilities/{id}")
    public EntityModel<Facility> updateFacility(@PathVariable Integer id, @RequestBody Facility facility) {
        facility = facilityService.updateFacility(id, facility);
        log.info("Facility Updated with ID: {}", id);
        return EntityModel.of(facility,
                linkTo(methodOn(FacilityController.class).getFacility(facility.getId())).withSelfRel(),
                linkTo(methodOn(FacilityController.class).getAllFacilities()).withRel("facilities"));
    }

    @GetMapping("/facilities/{id}")
    public EntityModel<Facility> getFacility(@PathVariable Integer id) {
        Facility facility = facilityService.getFacility(id);

        return EntityModel.of(facility,
                linkTo(methodOn(FacilityController.class).getFacility(id)).withSelfRel(),
                linkTo(methodOn(FacilityController.class).getAllFacilities()).withRel("facilities"));
    }
}
