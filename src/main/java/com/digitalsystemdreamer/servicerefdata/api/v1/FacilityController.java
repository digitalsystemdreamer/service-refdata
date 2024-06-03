package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dto.FacilityDto;
import com.digitalsystemdreamer.servicerefdata.service.FacilityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class FacilityController {
    @Autowired
    private FacilityService facilityService;
    @Autowired
    private Assembler assembler;

    @GetMapping("/facilities")
    public List<FacilityDto> getAllFacilities() {
        return facilityService.getAllFacilities();
    }

    @PostMapping("/facilities")
    public FacilityDto saveFacility(@RequestBody FacilityDto facilityDto) {
        FacilityDto facilityDto1 = facilityService.saveFacility(facilityDto);
        log.info("Facility Created with ID: {}", facilityDto1.getFacilityId());
        return facilityDto1;
    }

    @PutMapping("/facilities/{id}")
    public FacilityDto updateFacility(@PathVariable Integer id, @RequestBody FacilityDto facilityDto) {
        FacilityDto facilityDto1 = facilityService.updateFacility(id, facilityDto);
        log.info("Facility Updated with ID: {}", id);
        return facilityDto1;
    }

    @GetMapping("/facilities/{id}")
    public FacilityDto getFacility(@PathVariable Integer id) {
        return facilityService.getFacility(id);
    }

    @QueryMapping
    public FacilityDto facilityById(@Argument String id) {
        return facilityService.getFacility(Integer.valueOf(id));
    }

}
