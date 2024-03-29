package com.nashss.se.musicplaylistservice.activity.organizationActivities;

import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.DeleteOrganizationRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.DeleteOrganizationResult;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;

import javax.inject.Inject;

/**
 * Implementation of DeleteOrganizationActivity for Project Binford's GetOrganization API
 *
 * This API allows a consumer to retrieve a single Organization object by orgId
 */
public class DeleteOrganizationActivity {
    private final OrganizationDao organizationDao;

    /**
     * Instantiates a new DeleteOrganizationActivity object
     *
     * @param organizationDao OrganizationDao to interact with the org table
     */

    @Inject
    public DeleteOrganizationActivity(OrganizationDao organizationDao) {
        this. organizationDao = organizationDao;
    }

    /**
     * This method handles the request by deleting a single org from the database, if present
     * @param deleteOrganizationRequest request object containing an orgId
     * @return DeleteOrganizationResult, and empty object
     */
    public DeleteOrganizationResult handleRequest(final DeleteOrganizationRequest deleteOrganizationRequest) {
        Organization organization = new Organization();
        organization.setOrgId(deleteOrganizationRequest.getOrgId());
        organizationDao.deleteOrganization(organization);
        return DeleteOrganizationResult.builder()
                .withOrganization(organization)
                .build();
    }
}
