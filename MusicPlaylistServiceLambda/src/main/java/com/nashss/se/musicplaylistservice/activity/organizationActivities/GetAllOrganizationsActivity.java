package com.nashss.se.musicplaylistservice.activity.organizationActivities;

import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.GetAllOrganizationsRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.GetAllOrganizationsResult;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;

import javax.inject.Inject;

/**
 * Implementation of GetAllOrganizationsActivity for Project Binford's GetAllOrganizations API
 *
 * This API allows a consumer to retrieve a list of Organization objects by orgId
 */
public class GetAllOrganizationsActivity {
    private final OrganizationDao organizationDao;

    /**
     * Instantiates a new GetAllOrganizationsActivity object
     *
     * @param organizationDao OrganizationDao to interact with the org table
     */

    @Inject
    public GetAllOrganizationsActivity(OrganizationDao organizationDao) {
        this. organizationDao = organizationDao;
    }

    /**
     * This method handles the request by retrieving all available orgs from the database
     * @return GetAllOrganizationResults object containing a list of {@link com.nashss.se.musicplaylistservice.dynamodb.models.Organization}
     */
    public GetAllOrganizationsResult handleRequest(final GetAllOrganizationsRequest getAllOrganizationsRequest) {
        return GetAllOrganizationsResult.builder()
                .withOrganizationList(organizationDao.getAllOrgs())
                .build();
    }
}
