package com.tmax.cmp;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AmazonDTO {
    private String instanceId;
    private String imageId;
    private String keyName;
    private String subnetId;
    private String vpcId;
    private String privateIpAddress;
    private String architecture;
    private String rootDeviceType;
    private String rootDeviceName;
    private String virtualizationType;
    private String hypervisor;
}
