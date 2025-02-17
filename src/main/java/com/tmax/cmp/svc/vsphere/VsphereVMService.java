package com.tmax.cmp.svc.vsphere;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmax.cmp.entity.vsphere.vm.vmresources.*;
import com.tmax.cmp.repository.VsphereVMRepository;
import com.vmware.vapi.bindings.StubConfiguration;
import com.vmware.vapi.bindings.StubFactory;
import com.vmware.vcenter.VM;
import com.vmware.vcenter.VMTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class VsphereVMService {

    @Autowired
    VsphereVMRepository vsphereVMRepository;

    @Autowired
    AuthUtils authUtils;

    public void getVM(StubFactory stubFactory, StubConfiguration sessionStubConfig,
                      String vmName){

        VM vmservice = stubFactory.createStub(VM.class, sessionStubConfig);

        VMTypes.FilterSpec vmFilterSpec = new VMTypes.FilterSpec
                .Builder().setNames(Collections.singleton(vmName)).build();

        VMTypes.Info vmList = vmservice.get(vmName);


        System.out.println(vmList);

    }

    public void parseJsonToObject(String jsonData) throws JsonProcessingException {

        jsonData = "{\n" +
                "    \"instant_clone_frozen\": false,\n" +
                "    \"cdroms\": {\n" +
                "        \"3000\": {\n" +
                "            \"start_connected\": false,\n" +
                "            \"backing\": {\n" +
                "                \"device_access_type\": \"PASSTHRU\",\n" +
                "                \"type\": \"CLIENT_DEVICE\"\n" +
                "            },\n" +
                "            \"allow_guest_control\": true,\n" +
                "            \"ide\": {\n" +
                "                \"master\": true,\n" +
                "                \"primary\": true\n" +
                "            },\n" +
                "            \"label\": \"CD/DVD drive 1\",\n" +
                "            \"state\": \"NOT_CONNECTED\",\n" +
                "            \"type\": \"IDE\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"memory\": {\n" +
                "        \"hot_add_increment_size_MiB\": 0,\n" +
                "        \"size_MiB\": 8192,\n" +
                "        \"hot_add_enabled\": false,\n" +
                "        \"hot_add_limit_MiB\": 8192\n" +
                "    },\n" +
                "    \"disks\": {\n" +
                "        \"2000\": {\n" +
                "            \"scsi\": {\n" +
                "                \"bus\": 0,\n" +
                "                \"unit\": 0\n" +
                "            },\n" +
                "            \"backing\": {\n" +
                "                \"vmdk_file\": \"[datastore2] shkim-vsphere-ft6sp/shkim-vsphere-ft6sp.vmdk\",\n" +
                "                \"type\": \"VMDK_FILE\"\n" +
                "            },\n" +
                "            \"label\": \"Hard disk 1\",\n" +
                "            \"type\": \"SCSI\",\n" +
                "            \"capacity\": 26843545600\n" +
                "        }\n" +
                "    },\n" +
                "    \"parallel_ports\": {},\n" +
                "    \"sata_adapters\": {},\n" +
                "    \"cpu\": {\n" +
                "        \"hot_remove_enabled\": false,\n" +
                "        \"count\": 4,\n" +
                "        \"hot_add_enabled\": false,\n" +
                "        \"cores_per_socket\": 4\n" +
                "    },\n" +
                "    \"scsi_adapters\": {\n" +
                "        \"1000\": {\n" +
                "            \"pci_slot_number\": 160,\n" +
                "            \"scsi\": {\n" +
                "                \"bus\": 0,\n" +
                "                \"unit\": 7\n" +
                "            },\n" +
                "            \"label\": \"SCSI controller 0\",\n" +
                "            \"sharing\": \"NONE\",\n" +
                "            \"type\": \"PVSCSI\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"power_state\": \"POWERED_ON\",\n" +
                "    \"floppies\": {},\n" +
                "    \"identity\": {\n" +
                "        \"name\": \"shkim-vsphere-ft6sp\",\n" +
                "        \"instance_uuid\": \"fde45a99-fb43-49fb-a993-ad352f657fc3\",\n" +
                "        \"bios_uuid\": \"421b5fd0-3c85-3e3e-4a76-96de033de204\"\n" +
                "    },\n" +
                "    \"nvme_adapters\": {},\n" +
                "    \"name\": \"shkim-vsphere-ft6sp\",\n" +
                "    \"nics\": {\n" +
                "        \"4000\": {\n" +
                "            \"start_connected\": true,\n" +
                "            \"pci_slot_number\": 192,\n" +
                "            \"backing\": {\n" +
                "                \"network_name\": \"VM Network\",\n" +
                "                \"type\": \"STANDARD_PORTGROUP\",\n" +
                "                \"network\": \"network-13\"\n" +
                "            },\n" +
                "            \"mac_address\": \"00:50:56:9b:03:cf\",\n" +
                "            \"mac_type\": \"ASSIGNED\",\n" +
                "            \"allow_guest_control\": true,\n" +
                "            \"wake_on_lan_enabled\": true,\n" +
                "            \"label\": \"Network adapter 1\",\n" +
                "            \"state\": \"CONNECTED\",\n" +
                "            \"type\": \"VMXNET3\",\n" +
                "            \"upt_compatibility_enabled\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"boot\": {\n" +
                "        \"delay\": 0,\n" +
                "        \"retry_delay\": 10000,\n" +
                "        \"enter_setup_mode\": false,\n" +
                "        \"type\": \"BIOS\",\n" +
                "        \"retry\": false\n" +
                "    },\n" +
                "    \"serial_ports\": {},\n" +
                "    \"boot_devices\": [\n" +
                "        {\n" +
                "            \"disks\": [\n" +
                "                \"2000\"\n" +
                "            ],\n" +
                "            \"type\": \"DISK\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"guest_OS\": \"OTHER\",\n" +
                "    \"hardware\": {\n" +
                "        \"upgrade_policy\": \"NEVER\",\n" +
                "        \"upgrade_status\": \"NONE\",\n" +
                "        \"version\": \"VMX_15\"\n" +
                "    }\n" +
                "}";


        ObjectMapper mapper = new ObjectMapper();
        VsphereVM vm = mapper.readValue(jsonData,VsphereVM.class);
        vsphereVMRepository.save(vm);

    }

    public ArrayList<String> getVMListFromServer(VM vmService) throws Exception{

        List<VMTypes.Summary> vmList = vmService.list(new VMTypes.FilterSpec());
        ArrayList<String> vmNames = new ArrayList<>();

        System.out.println("total vm Count: " + vmList.size());
        for(VMTypes.Summary summary : vmList){
            System.out.println(summary);
            vmNames.add(summary.getVm());
        }

        return vmNames;
    }

    public VMTypes.Info getVMInfoFromServer(VM vmService, String vmName) throws Exception{

        VMTypes.Info vmInfo = vmService.get(vmName);

        System.out.println(vmInfo);

        return vmInfo;
    }

    public void syncVMFromServerToDB(VM vmClient) throws Exception{

        List<String> vmNames = getVMListFromServer(vmClient);
        int mappingSuccess = 0;
        int mappingFail = 0;

        for(String vmName : vmNames){
            try{
                VMTypes.Info vmInfo = getVMInfoFromServer(vmClient, vmName);
                VsphereVM vm = VsphereVM.builder()
                        .boot(
                                Boot.builder().delay(vmInfo.getBoot().getDelay()).efi_legacy_boot(vmInfo
                                        .getBoot().getEfiLegacyBoot()).enter_setup_mode(vmInfo.getBoot().getEnterSetupMode())
                                        .network_protocol(vmInfo.getBoot().getNetworkProtocol()).retry(vmInfo.getBoot().getRetry())
                                        .retry_delay(vmInfo.getBoot().getRetryDelay()).type(vmInfo.getBoot().getType()).build())
                        .identity(
                                Identity.builder().bios_uuid(vmInfo.getIdentity().getBiosUuid())
                                        .instance_uuid(vmInfo.getIdentity().getInstanceUuid())
                                        .name(vmInfo.getName()).build()
                        )
                        .guest_OS(vmInfo.getGuestOS().toString())
                        .hardware(
                                Hardware.builder().version(vmInfo.getHardware().getVersion().name())
                                        .upgrade_policy(vmInfo.getHardware().getUpgradePolicy().name())
                                        .upgrade_version(vmInfo.getHardware().getUpgradeVersion())      //upgrade version은 String으로 받을 시 insert 까지 적용 안됨 (단순 null error?)
                                        .upgrade_status(vmInfo.getHardware().getUpgradeStatus().name())
                                        .upgrade_error(vmInfo.getHardware().getUpgradeError()).build())
                        .instant_clone_frozen(vmInfo.getInstantCloneFrozen())
                        .memory(
                                Memory.builder().size_MiB(vmInfo.getMemory().getSizeMiB())
                                        .hot_add_enabled(vmInfo.getMemory().getHotAddEnabled())
                                        .hot_add_increment_size_MiB(vmInfo.getMemory().getHotAddIncrementSizeMiB())
                                        .hot_add_limit_MiB(vmInfo.getMemory().getHotAddLimitMiB()).build())
                        .name(vmInfo.getName())
                        .power_state(vmInfo.getPowerState().getEnumValue().toString())
                        .cpu(
                                Cpu.builder()
                                        .count(vmInfo.getCpu().getCount())
                                        .cores_per_socket(vmInfo.getCpu().getCoresPerSocket())
                                        .hot_add_enabled(vmInfo.getCpu().getHotAddEnabled())
                                        .hot_remove_enabled(vmInfo.getCpu().getHotAddEnabled()).build()
                        )

                        .build();

                mappingSuccess++;
                vsphereVMRepository.save(vm);
            }catch (Exception e){
                mappingFail++;
                System.out.println("error occured while mapping vm to dto");
                System.out.println("mapping fail vm: " + vmName);
                e.getMessage();
            }

        }

        System.out.println("total mapping success: " + mappingSuccess);
        System.out.println("total mapping fail: " + mappingFail);
    }

}
