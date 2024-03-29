package com.nashss.se.musicplaylistservice.activity.organizationActivities;

import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.CreateOrganizationRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.CreateOrganizationResult;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;

import javax.inject.Inject;

/**
 * Implementation of CreateOrganizationActivity for Project Binford's Organizations API
 *
 * This API allows a consumer to write an Organization to the database
 */
public class CreateOrganizationActivity {
    private final OrganizationDao organizationDao;

    /**
     * Instantiates a new CreateOrganizationActivity object
     *
     * @param organizationDao OrganizationDao to interact with the org table
     */

    @Inject
    public CreateOrganizationActivity(OrganizationDao organizationDao) {
        this. organizationDao = organizationDao;
    }

    /**
     * This method handles the request by accepting a CreateOrganizationActivity and creating a new Organization object to write to the DB
     * @return single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Organization} that was just written
     */
    public CreateOrganizationResult handleRequest(final CreateOrganizationRequest createOrganizationRequest) {
        if(createOrganizationRequest.getOrgId() == null || createOrganizationRequest.getDisplayName() == null) {
            throw new InvalidAttributeValueException("Error creating Organization resource: required value cannot be null");
        }
        Organization organization = new Organization();
        organization.setOrgId(createOrganizationRequest.getOrgId());
        organization.setDisplayName(createOrganizationRequest.getDisplayName());

        return CreateOrganizationResult.builder()
                .withOrganization(organizationDao.writeOrganzation(organization))
                .build();
    }
}
