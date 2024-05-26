package com.digitalsystemdreamer.servicerefdata.service;

import com.digitalsystemdreamer.servicerefdata.dao.BillingRepo;
import com.digitalsystemdreamer.servicerefdata.dao.PackageRepo;
import com.digitalsystemdreamer.servicerefdata.dto.BillingDto;
import com.digitalsystemdreamer.servicerefdata.dto.PackageDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnumService {

    @Autowired
    private BillingRepo billingRepo;

    @Autowired
    private PackageRepo packageRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<BillingDto> getAllBillingEnums() {
        return modelMapper.map(billingRepo.findAll(), new TypeToken<List<BillingDto>>(){}.getType());
    }

    public List<PackageDto> getAllPackageEnums() {
        return modelMapper.map(packageRepo.findAll(), new TypeToken<List<PackageDto>>(){}.getType());
    }

}
