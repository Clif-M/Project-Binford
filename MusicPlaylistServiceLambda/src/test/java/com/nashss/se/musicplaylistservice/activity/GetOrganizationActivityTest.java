package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.organizationActivities.GetOrganizationActivity;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.GetOrganizationRequest;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetOrganizationActivityTest {
    @Mock
    private OrganizationDao organizationDao;

    private GetOrganizationActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetOrganizationActivity(organizationDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN
        String orgId = "testId";
        Organization organization = new Organization();
        GetOrganizationRequest request = GetOrganizationRequest.builder()
                                            .withOrgId(orgId)
                                            .build();

        doReturn(organization).when(organizationDao).getSingleOrganization(orgId);

        // WHEN
        activity.handleRequest(request);

        // THEN
        verify(organizationDao).getSingleOrganization(orgId);
    }
}
