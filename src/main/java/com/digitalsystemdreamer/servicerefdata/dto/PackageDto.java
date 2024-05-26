package com.digitalsystemdreamer.servicerefdata.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PackageDto extends EnumType{
    Integer packageId;
    String packageName;
}
