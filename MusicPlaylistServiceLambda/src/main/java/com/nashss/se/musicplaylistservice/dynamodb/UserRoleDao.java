package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import com.nashss.se.musicplaylistservice.exceptions.UserRoleNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public boolean checkIfExist(UserRole userRole) {
        UserRole loaded = mapper.load(UserRole.class, userRole.getUserEmail(), userRole.getOrgId());
        return loaded != null;
    }


    /**
     * Retrieves all userRoles matching provided orgId.
     *
     * @param userRole The UserRole to be saved
     */
    public void writeUserRole(UserRole userRole) {
        mapper.save(userRole);
    }

    /**
     * Retrieves all userRoles matching provided orgId
     *
     * If none found, returns an empty list.
     *
     * @param orgId The orgId to look up
     * @return A list of UserRoles found, if any
     */

    public List<UserRole> getUsersForOrg(String orgId) {
        Map<String, AttributeValue>  valueMap = new HashMap<>();
        valueMap.put(":orgId", new AttributeValue(orgId));
        DynamoDBQueryExpression<UserRole> queryExpression = new DynamoDBQueryExpression<UserRole>()
                .withIndexName("GetUsersForOrgIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("orgId = :orgId")
                .withExpressionAttributeValues(valueMap);
        return mapper.query(UserRole.class, queryExpression);
    }


}
