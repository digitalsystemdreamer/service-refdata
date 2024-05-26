package com.digitalsystemdreamer.servicerefdata.dto;

import lombok.Data;

@Data
public class BillingDto extends EnumType{
    Integer billingId;
    String name;
    Integer billingRate;
}
