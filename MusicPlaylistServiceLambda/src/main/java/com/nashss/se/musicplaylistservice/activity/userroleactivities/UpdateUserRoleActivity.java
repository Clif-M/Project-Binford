package com.nashss.se.musicplaylistservice.activity.userroleactivities;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.UpdateUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.UpdateUserRoleResult;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.musicplaylistservice.exceptions.UserRoleNotFoundException;

import javax.inject.Inject;


public class UpdateUserRoleActivity {
    private final UserRoleDao userRoleDao;

    /**
     * Instantiates a new UpdateUserRoleActivity object
     *
     * @param userRoleDao UserRoleDao to interact with the userRole table
     */
    @Inject
    public UpdateUserRoleActivity(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    /**
     * This method handles the request by accepting an UpdateMaterialRequest object
     * and saves the updated object to the DB
     * @return {@link com.nashss.se.musicplaylistservice.dynamodb.models.Material}
     */
    public UpdateUserRoleResult handleRequest(UpdateUserRoleRequest updateUserRoleRequest) {
        if (updateUserRoleRequest.getUserEmail() == null || updateUserRoleRequest.getOrgId() == null) {
            throw new InvalidAttributeValueException("Error updating User Role resource: required value cannot be null");
        }
        UserRole userRole = userRoleDao.loadUserRole(updateUserRoleRequest.getUserEmail(), updateUserRoleRequest.getOrgId());
        userRole.setUserEmail(updateUserRoleRequest.getUserEmail());
        userRole.setOrgId(updateUserRoleRequest.getOrgId());
        userRole.setJobRole(updateUserRoleRequest.getJobRole());
        userRole.setDisplayName(updateUserRoleRequest.getDisplayName());
        userRole.setRoleStatus(updateUserRoleRequest.getRoleStatus());

        userRoleDao.writeUserRole(userRole);

        return UpdateUserRoleResult.builder()
                .withUserRole(userRole)
                .build();
    }
}
