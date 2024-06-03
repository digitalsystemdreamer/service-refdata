package com.digitalsystemdreamer.servicerefdata.api.v2;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dto.BillingDto;
import com.digitalsystemdreamer.servicerefdata.dto.PackageDto;
import com.digitalsystemdreamer.servicerefdata.service.EnumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RequestMapping("/api/v2/enums/")
@Slf4j
public class EnumControllerV2 {

    @Autowired
    private EnumService enumService;
    @Autowired
    private Assembler assembler;

    @GetMapping
    public CollectionModel<EntityModel<BillingDto>> getAllEnums() {
        List<EntityModel<BillingDto>> allBillingEnums = enumService.getAllBillingEnums().stream()
        .map(EntityModel::of)
                .toList();
        List<EntityModel<PackageDto>> allPackageEnums = enumService.getAllPackageEnums().stream()
                .map(EntityModel::of)
                .toList();
        List finalList = new ArrayList<>();
        finalList.add(allBillingEnums);
        finalList.add(allPackageEnums);
        return CollectionModel.of(finalList, linkTo(methodOn(EnumControllerV2.class).getAllEnums()).withSelfRel());
    }
}
