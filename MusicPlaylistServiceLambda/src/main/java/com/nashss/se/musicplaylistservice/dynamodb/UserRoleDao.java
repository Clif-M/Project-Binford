package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import com.nashss.se.musicplaylistservice.exceptions.UserRoleNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Accesses data for a UserRole using {@link UserRole} to interact with the model in DynamoDB.
 */

@Singleton
public class UserRoleDao {
    private final DynamoDBMapper mapper;

    /**
     * Instantiates a UserRoleDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the UserRole table
     */

    @Inject
    public UserRoleDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves a UserRole by userEmail.
     *
     * If not found, throws UserRoleNotFoundException.
     *
     * @param userEmail The userEmail to look up
     * @param orgId The orgId to look up
     * @return The corresponding UserRole if found
     */
    public UserRole loadUserRole(String userEmail, String orgId) {
        UserRole userRole = mapper.load(UserRole.class, userEmail, orgId);
        if (userRole == null) {
            throw new UserRoleNotFoundException(String.format("Could not find User Role with userEmail %s, and orgId %s", userEmail, orgId));
        }
        return userRole;
    }

    /**
     * Retrieves all UserRoles matching provided userEmail.
     *
     * If none found, returns an empty list.
     *
     * @param userEmail The userEmail to look up
     * @return A list of userRoles found, if any
     */
    public List<UserRole> loadUserRolesForEmail(String userEmail) {
        UserRole userRole = new UserRole();
        userRole.setUserEmail(userEmail);
        DynamoDBQueryExpression<UserRole> queryExpression = new DynamoDBQueryExpression<UserRole>()
                .withHashKeyValues(userRole);
        return mapper.query(UserRole.class, queryExpression);
    }


    /**
     * Saves provided UserRole to DynamoDB to create or update DynamoDB record.
     *
     * @param userRole The UserRole to be saved
     */
    public void writeUserRole(UserRole userRole) {
        mapper.save(userRole);
    }


}
