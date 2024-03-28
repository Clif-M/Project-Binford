package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.organizationActivities.DeleteOrganizationActivity;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.DeleteOrganizationRequest;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteOrganizationActivityTest {
    @Mock
    private OrganizationDao organizationDao;

    private DeleteOrganizationActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new DeleteOrganizationActivity(organizationDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN
        String orgId = "testId";
        Organization organization = new Organization();
        DeleteOrganizationRequest request = DeleteOrganizationRequest.builder()
                                            .withOrgId(orgId)
                                            .build();

        // WHEN
        activity.handleRequest(request);

        // THEN
        verify(organizationDao).deleteOrganization(any(Organization.class));
    }
}
