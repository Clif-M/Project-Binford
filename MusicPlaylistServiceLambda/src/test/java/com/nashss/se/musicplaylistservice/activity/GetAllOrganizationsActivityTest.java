package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.organizationActivities.GetAllOrganizationsActivity;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.GetAllOrganizationsRequest;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllOrganizationsActivityTest {
    @Mock
    private OrganizationDao organizationDao;

    private GetAllOrganizationsActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetAllOrganizationsActivity(organizationDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN

        GetAllOrganizationsRequest request = GetAllOrganizationsRequest.builder().build();

        doReturn(new ArrayList<>()).when(organizationDao).getAllOrgs();

        // WHEN
        activity.handleRequest(request);

        // THEN
        verify(organizationDao).getAllOrgs();
    }
}
