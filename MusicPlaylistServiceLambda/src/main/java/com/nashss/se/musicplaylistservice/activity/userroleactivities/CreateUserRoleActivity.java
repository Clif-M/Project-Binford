package com.nashss.se.musicplaylistservice.activity.userroleactivities;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.CreateUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.CreateUserRoleResult;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import com.nashss.se.musicplaylistservice.exceptions.UserRoleAlreadyExistException;
import com.nashss.se.musicplaylistservice.exceptions.UserRoleNotFoundException;

import javax.inject.Inject;
import java.util.UUID;

public class CreateUserRoleActivity {
    private final UserRoleDao userRoleDao;

    /**
     * Instantiates a new CreateUserRoleActivity object
     *
     * @param userRoleDao UserRoleDao to interact with the material table
     */

    @Inject
    public CreateUserRoleActivity(UserRoleDao userRoleDao) {
        this.userRoleDao=userRoleDao;
    }

    /**
     * This method handles the request by accepting a CreateUserRoleRequest object
     * and creating a new UserRole object to write to the DB
     * @return single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Material} that was just written
     */
    public CreateUserRoleResult handleRequest(CreateUserRoleRequest createUserRoleRequest) {
        if (createUserRoleRequest.getUserEmail() == null || createUserRoleRequest.getOrgId() == null) {
            throw new InvalidAttributeValueException("Error creating UserRole resource:" +
                    " required values cannot be null(User Email, OrgId)");
        }
        UserRole userRole = new UserRole();
        userRole.setUserEmail(createUserRoleRequest.getUserEmail());
        userRole.setOrgId(createUserRoleRequest.getOrgId());
        userRole.setJobRole(createUserRoleRequest.getJobRole());
        userRole.setDisplayName(createUserRoleRequest.getDisplayName());
        userRole.setRoleStatus(createUserRoleRequest.getRoleStatus());

        if (userRoleDao.checkIfExist(userRole)) {
            throw new UserRoleAlreadyExistException("UserRole already exist in the Database");
        }
        userRoleDao.writeUserRole(userRole);

        return CreateUserRoleResult.builder()
                .withUserRole(userRole)
                .build();
    }
}
