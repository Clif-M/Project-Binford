package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.organizationActivities.UpdateOrganizationActivity;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.UpdateOrganizationRequest;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateOrganizationActivityTest {
    @Mock
    private OrganizationDao organizationDao;

    private UpdateOrganizationActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new UpdateOrganizationActivity(organizationDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesOrganizationName() {
        // GIVEN
        String orgId = "testId";
        String oldName = "oldName";
        String newName = "newName";

        UpdateOrganizationRequest request = UpdateOrganizationRequest.builder()
                                                .withOrgId(orgId)
                                                .withDisplayName(newName)
                                                .build();

        Organization start = new Organization();
        start.setOrgId(orgId);
        start.setDisplayName(oldName);

        Organization end = new Organization();
        end.setOrgId(orgId);
        end.setDisplayName(newName);

        ArgumentCaptor<Organization> argumentCaptor = ArgumentCaptor.forClass(Organization.class);

        doReturn(start).when(organizationDao).getSingleOrganization(orgId);
        doReturn(end).when(organizationDao).writeOrganzation(any(Organization.class));

        // WHEN
        activity.handleRequest(request);
        verify(organizationDao).writeOrganzation(argumentCaptor.capture());

        // THEN
        verify(organizationDao).getSingleOrganization(any(String.class));
        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected class to pass original orgId to DAO for write");
        assertEquals(newName, argumentCaptor.getValue().getDisplayName(), "Expected class to pass updated displayName to DAO for write");
    }

   @Test
    public void handleRequest_nullOrgId_InvalidAttributeValueException() {
        // GIVEN
        String orgId = null;
        String newName = "newName";

        UpdateOrganizationRequest request = UpdateOrganizationRequest.builder()
                .withOrgId(orgId)
                .withDisplayName(newName)
                .build();

        // WHEN
        // THEN
       assertThrows(InvalidAttributeValueException.class, () -> activity.handleRequest(request), "Expected method to throw error when provided null value");
    }

    @Test
    public void handleRequest_nullName_InvalidAttributeValueException() {
        // GIVEN
        String orgId = "testId";
        String newName = null;

        UpdateOrganizationRequest request = UpdateOrganizationRequest.builder()
                .withOrgId(orgId)
                .withDisplayName(newName)
                .build();

        // WHEN
        // THEN
        assertThrows(InvalidAttributeValueException.class, () -> activity.handleRequest(request), "Expected method to throw error when provided null value");
    }

}
