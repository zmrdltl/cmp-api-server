package com.tmax.cmp.entity.common.client;

import lombok.Getter;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.costandusagereport.CostAndUsageReportClient;
import software.amazon.awssdk.services.costexplorer.CostExplorerClient;
import software.amazon.awssdk.services.ec2.Ec2Client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class awsClient implements Client{

    private static final List<Region> REGIONS = Collections.unmodifiableList(Arrays.asList(Region.AP_SOUTH_1, Region.EU_SOUTH_1,
            Region.CA_CENTRAL_1, Region.EU_CENTRAL_1, Region.US_WEST_1, Region.US_WEST_2, Region.EU_NORTH_1, Region.EU_WEST_3, Region.EU_WEST_2,
            Region.EU_WEST_1, Region.AP_NORTHEAST_3, Region.AP_NORTHEAST_2, Region.AP_NORTHEAST_1, Region.SA_EAST_1, Region.AP_EAST_1, Region.CN_NORTH_1,
            Region.AP_SOUTHEAST_1, Region.AP_SOUTHEAST_2, Region.US_EAST_1, Region.US_EAST_2));
    private Ec2Client ec2Client;
    private CostExplorerClient costExplorerClient;
    private CostAndUsageReportClient costAndUsageReportClient;

    @Getter
    private List<CostExplorerClient> costExplorerClients;
    @Getter
    private List<Ec2Client> ec2Clients;
    @Getter
    private List<CostAndUsageReportClient> costAndUsageReportClients;

    public awsClient(String accessKey, String secretKey) {
        AwsBasicCredentials awsCredential = AwsBasicCredentials.create(accessKey, secretKey);
        this.costExplorerClients = new ArrayList<CostExplorerClient>();
        this.costAndUsageReportClients = new ArrayList<CostAndUsageReportClient>();
        this.ec2Clients = new ArrayList<Ec2Client>();

        for(Region region : REGIONS) {
            ec2Client = Ec2Client.builder()
                    .credentialsProvider(StaticCredentialsProvider.create(awsCredential))
                    .region(region)
                    .build();
            ec2Clients.add(ec2Client);
        }

        costExplorerClient = CostExplorerClient.builder().credentialsProvider(StaticCredentialsProvider.create(awsCredential))
                .region(Region.US_EAST_1)
                .build();

        costAndUsageReportClient = CostAndUsageReportClient.builder().credentialsProvider(StaticCredentialsProvider.create(awsCredential))
                        .region(Region.US_EAST_1).build();

        costExplorerClients.add(costExplorerClient);
        costAndUsageReportClients.add(costAndUsageReportClient);
    }
}
