package com.nashss.se.musicplaylistservice.activity.organizationActivities;

import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.UpdateOrganizationRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.UpdateOrganizationResult;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;

import javax.inject.Inject;

/**
 * Implementation of UpdateOrganizationActivity for Project Binford's Organizations API
 *
 * This API allows a consumer to update an existing Organization in the database
 */
public class UpdateOrganizationActivity {
    private final OrganizationDao organizationDao;

    /**
     * Instantiates a new UpdateOrganizationActivity object
     *
     * @param organizationDao OrganizationDao to interact with the org table
     */

    @Inject
    public UpdateOrganizationActivity(OrganizationDao organizationDao) {
        this. organizationDao = organizationDao;
    }

    /**
     * This method handles the request by accepting a UpdateOrganizationActivity and creating a new Organization object to write to the DB
     * @return single {@link Organization} that was just written
     */
    public UpdateOrganizationResult handleRequest(final UpdateOrganizationRequest updateOrganizationRequest) {
        if(updateOrganizationRequest.getOrgId() == null || updateOrganizationRequest.getDisplayName() == null) {
            throw new InvalidAttributeValueException("Error updating Organization resource: required value cannot be null");
        }

        Organization organization = organizationDao.getSingleOrganization(updateOrganizationRequest.getOrgId());
        organization.setDisplayName(updateOrganizationRequest.getDisplayName());

        return UpdateOrganizationResult.builder()
                .withOrganization(organizationDao.writeOrganzation(organization))
                .build();
    }
}
