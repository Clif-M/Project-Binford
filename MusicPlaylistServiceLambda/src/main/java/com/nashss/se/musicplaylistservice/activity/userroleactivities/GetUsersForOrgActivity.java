package com.nashss.se.musicplaylistservice.activity.userroleactivities;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUserRolesRequest;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUsersForOrgRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUserRolesResult;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUsersForOrgResult;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetUsersForOrgActivity {
    private final UserRoleDao userRoleDao;

    /**
     * Instantiates a new GetUsersForOrgActivity object.
     *
     * @param userRoleDao TaskDao to access the playlist table.
     */
    @Inject
    public GetUsersForOrgActivity(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    /**
     * This method handles the incoming request by retrieving list of userRoles belong to an org from the database, if any exist.
     * <p>
     * It then returns the list.
     * <p>
     * If no tasks are found, the method will return an empty list.
     *
     * @param getUsersForOrgRequest request object containing the orgId
     * @return GetUsersForOrgResult result object containing the API defined {@link List<UserRole>}
     */

    public GetUsersForOrgResult handleRequest(final GetUsersForOrgRequest getUsersForOrgRequest) {
        List<UserRole> userRoles = new ArrayList<>();
        userRoles = userRoleDao.getUsersForOrg(getUsersForOrgRequest.getOrgId());
        return GetUsersForOrgResult.builder()
                .withUserRoleList(userRoles)
                .build();
    }


}
