package com.nashss.se.musicplaylistservice.activity.organizationActivities;

import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.GetOrganizationRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.GetOrganizationResult;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;

import javax.inject.Inject;

/**
 * Implementation of GetOrganizationActivity for Project Binford's GetOrganization API
 *
 * This API allows a consumer to retrieve a single Organization object by orgId
 */
public class GetOrganizationActivity {
    private final OrganizationDao organizationDao;

    /**
     * Instantiates a new GetOrganizationActivity object
     *
     * @param organizationDao OrganizationDao to interact with the org table
     */

    @Inject
    public GetOrganizationActivity(OrganizationDao organizationDao) {
        this. organizationDao = organizationDao;
    }

    /**
     * This method handles the request by retrieving a single org from the database
     * @param getOrganizationRequest request object containing an orgId
     * @return GetOrganizationResult object containing a single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Organization}
     */
    public GetOrganizationResult handleRequest(final GetOrganizationRequest getOrganizationRequest) {
        return GetOrganizationResult.builder()
                .withOrganization(organizationDao.getSingleOrganization(getOrganizationRequest.getOrgId()))
                .build();
    }
}
