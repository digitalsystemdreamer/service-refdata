package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.model.Facility;
import com.digitalsystemdreamer.servicerefdata.service.FacilityService;
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
@RequestMapping("/api/vi")
@Slf4j
public class FacilityController {
    @Autowired
    private FacilityService facilityService;

    @GetMapping("/facilities")
    public CollectionModel<EntityModel<Facility>> getAllFacilities(){
        List<Facility> all = facilityService.getAllFacilities();
        List<EntityModel<Facility>> allFacilities = all.stream()
                .map(membership -> EntityModel.of(membership,
                        linkTo(methodOn(FacilityController.class).getFacility(membership.getId())).withSelfRel(),
                        linkTo(methodOn(FacilityController.class).getAllFacilities()).withRel("facilities")))
                        .collect(Collectors.toList());
        return CollectionModel.of(allFacilities, linkTo(methodOn(FacilityController.class).getAllFacilities()).withSelfRel());
    }
    @PostMapping("/facilities")
    public EntityModel<Facility> saveFacility(@RequestBody Facility membership){
        Facility membership1 = facilityService.saveFacility(membership);
        log.info("Facility Created with ID: {}", membership1.getId());
        return EntityModel.of(membership1,
                linkTo(methodOn(FacilityController.class).getFacility(membership1.getId())).withSelfRel(),
                linkTo(methodOn(FacilityController.class).getAllFacilities()).withRel("facilities"));
    }

    @GetMapping("/facilities/{id}")
    public EntityModel<Facility> getFacility(@PathVariable Integer id){
        Facility membership = facilityService.getFacility(id);

        return EntityModel.of(membership,
                linkTo(methodOn(FacilityController.class).getFacility(id)).withSelfRel(),
                linkTo(methodOn(FacilityController.class).getAllFacilities()).withRel("facilities"));
    }
}
