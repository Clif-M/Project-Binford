package com.nashss.se.musicplaylistservice.activity.userroleactivities;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetDisplayRolesRequest;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUserRolesRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetDisplayRolesResult;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUserRolesResult;
import com.nashss.se.musicplaylistservice.dynamodb.OrganizationDao;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserDisplayRole;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetDisplayRolesActivity {
    private final UserRoleDao userRoleDao;
    private final OrganizationDao organizationDao;

    /**
     * Instantiates a new GetDisplayRolesActivity object.
     *
     * @param userRoleDao TaskDao to access the playlist table.
     */
    @Inject
    public GetDisplayRolesActivity(UserRoleDao userRoleDao, OrganizationDao organizationDao) {
        this.userRoleDao = userRoleDao;
        this.organizationDao = organizationDao;
    }

    /**
     * This method handles the incoming request by retrieving list of roles that belong to a user's email, then joins them with the human-readable org display name.
     * <p>
     * It then returns the list.
     * <p>
     * If no tasks are found, the method will return an empty list.
     *
     * @param getDisplayRolesRequest request object containing the orgId
     * @return GetUserRolesResult result object containing the API defined {@link List<UserRole>}
     */

    public GetDisplayRolesResult handleRequest(final GetDisplayRolesRequest getDisplayRolesRequest) {
        List<UserDisplayRole> userDisplayRoles = new ArrayList<>();
        List<UserRole> userRoles = userRoleDao.loadUserRolesForEmail(getDisplayRolesRequest.getUserEmail());
        for (UserRole userRole : userRoles) {
            userDisplayRoles.add(new UserDisplayRole(userRole.getUserEmail(),
                                                     userRole.getOrgId(),
                                                     userRole.getJobRole(),
                                                     userRole.getDisplayName(),
                                                     userRole.getRoleStatus(),
                                                     organizationDao.getSingleOrganization(userRole.getOrgId()).getDisplayName()
                    ));
        }
        return GetDisplayRolesResult.builder()
                .withUserDisplayRolesList(userDisplayRoles)
                .build();
    }


}
