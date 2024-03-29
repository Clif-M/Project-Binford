package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.organizationActivities.CreateOrganizationActivity;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.CreateOrganizationRequest;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateOrganizationActivityTest {
    @Mock
    private OrganizationDao organizationDao;

    private CreateOrganizationActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new CreateOrganizationActivity(organizationDao);
    }

    @Test
    public void handleRequest_goodRequest_returnsNewOrganization() {
        // GIVEN
        String orgId = "testId";
        String testName = "testName";


        CreateOrganizationRequest request = CreateOrganizationRequest.builder()
                                                .withOrgId(orgId)
                                                .withDisplayName(testName)
                                                .build();

        Organization organization = new Organization();
        organization.setOrgId(orgId);
        organization.setDisplayName(testName);

        ArgumentCaptor<Organization> argumentCaptor = ArgumentCaptor.forClass(Organization.class);

        doReturn(organization).when(organizationDao).writeOrganzation(any(Organization.class));

        // WHEN
        activity.handleRequest(request);
        verify(organizationDao).writeOrganzation(argumentCaptor.capture());

        // THEN
        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected class to pass provided orgId to DAO for write");
        assertEquals(testName, argumentCaptor.getValue().getDisplayName(), "Expected class to pass provided displayName to DAO for write");
    }

   @Test
    public void handleRequest_nullOrgId_InvalidAttributeValueException() {
        // GIVEN
        String orgId = null;
        String testName = "testName";

        CreateOrganizationRequest request = CreateOrganizationRequest.builder()
                .withOrgId(orgId)
                .withDisplayName(testName)
                .build();

        // WHEN
        // THEN
       assertThrows(InvalidAttributeValueException.class, () -> activity.handleRequest(request), "Expected method to throw error when provided null value");
    }

    @Test
    public void handleRequest_nullName_InvalidAttributeValueException() {
        // GIVEN
        String orgId = "testId";
        String testName = null;

        CreateOrganizationRequest request = CreateOrganizationRequest.builder()
                .withOrgId(orgId)
                .withDisplayName(testName)
                .build();

        // WHEN
        // THEN
        assertThrows(InvalidAttributeValueException.class, () -> activity.handleRequest(request), "Expected method to throw error when provided null value");
    }

}
