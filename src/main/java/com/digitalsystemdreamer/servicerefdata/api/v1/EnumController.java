package com.digitalsystemdreamer.servicerefdata.api.v1;

import com.digitalsystemdreamer.servicerefdata.assembler.Assembler;
import com.digitalsystemdreamer.servicerefdata.dto.BillingDto;
import com.digitalsystemdreamer.servicerefdata.dto.EnumType;
import com.digitalsystemdreamer.servicerefdata.dto.PackageDto;
import com.digitalsystemdreamer.servicerefdata.service.EnumService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/enums/")
@Slf4j
public class EnumController {

    @Autowired
    private EnumService enumService;
    @Autowired
    private Assembler assembler;

    @GetMapping
    public List<List<? extends EnumType>> getAllEnums() {
        List<BillingDto> allBillingEnums = enumService.getAllBillingEnums();
        List<PackageDto> allPackageEnums = enumService.getAllPackageEnums();
        return List.of(allBillingEnums, allPackageEnums);
    }
}
