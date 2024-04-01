package com.nashss.se.musicplaylistservice.activity.userroleactivities;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetSingleUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetSingleUserRoleResult;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;

import javax.inject.Inject;

/**
 * Implementation of GetUserRoleActivity for Project Binford's GetUserRole API
 *
 * This API allows a consumer to retrieve a single Organization object by orgId
 */
public class GetSingleUserRoleActivity {
    private final UserRoleDao userRoleDao;

    /**
     * Instantiates a new GetOrganizationActivity object
     *
     * @param userRoleDao OrganizationDao to interact with the org table
     */

    @Inject
    public GetSingleUserRoleActivity(UserRoleDao userRoleDao) {
        this. userRoleDao = userRoleDao;
    }

    /**
     * This method handles the request by retrieving a single org from the database
     * @param getSingleUserRoleRequest request object containing an orgId
     * @return GetOrganizationResult object containing a single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Organization}
     */
    public GetSingleUserRoleResult handleRequest(final GetSingleUserRoleRequest getSingleUserRoleRequest) {
        return GetSingleUserRoleResult.builder()
                .withSingleUserRole(userRoleDao.loadUserRole(getSingleUserRoleRequest.getUserEmail(), getSingleUserRoleRequest.getOrgId()))
                .build();
    }
}
