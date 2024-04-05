package com.nashss.se.musicplaylistservice.activity.userroleactivities;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUserRolesRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUserRolesResult;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class GetUserRolesActivity {
    private final UserRoleDao userRoleDao;

    /**
     * Instantiates a new GetTasksForOrgActivity object.
     *
     * @param userRoleDao TaskDao to access the playlist table.
     */
    @Inject
    public GetUserRolesActivity(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    /**
     * This method handles the incoming request by retrieving list of tasks belong to an org from the database, if any exist.
     * <p>
     * It then returns the list.
     * <p>
     * If no tasks are found, the method will return an empty list.
     *
     * @param getUserRolesRequest request object containing the orgId
     * @return GetUserRolesResult result object containing the API defined {@link java.util.List<UserRole>}
     */

    public GetUserRolesResult handleRequest(final GetUserRolesRequest getUserRolesRequest) {
        if (getUserRolesRequest.getUserEmail() == null) {
            throw new InvalidAttributeValueException("Error deleting loading resource: required value cannot be null");
        }
        List<UserRole> taskList = new ArrayList<>();
        taskList = userRoleDao.loadUserRolesForEmail(getUserRolesRequest.getUserEmail());
        return GetUserRolesResult.builder()
                .withUserRoleList(taskList)
                .build();
    }


}
