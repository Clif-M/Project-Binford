package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import com.nashss.se.musicplaylistservice.exceptions.OrganizationNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Accesses data for an organization using {@link Organization} to interact with the model in DynamoDB.
 */

@Singleton
public class OrganizationDao {
    private final DynamoDBMapper mapper;

    /**
     * Instantiates an OrganizationDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the Organizations table
     */

    @Inject
    public OrganizationDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves an organization by orgId.
     *
     * If not found, throws OrganizationNotFoundException.
     *
     * @param orgId The orgId to look up
     * @return The corresponding Organization if found
     */
    public Organization getSingleOrganization(String orgId) {
        Organization organization = mapper.load(Organization.class, orgId);
        if (null == organization) {
            throw new OrganizationNotFoundException(String.format("Could not find organization with orgId %s", orgId));
        }
        return organization;
    }

    /**
     * Retrieves all organizations in database.
     *
     * If none found, returns an empty list.
     *
     * @return A list of Organizations found, if any
     */
    public List<Organization> getAllOrgs() {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        List<Organization> result = mapper.scan(Organization.class, scanExpression);
        return result;
    }

    /**
     * Saves provided Organization to DynamoDB to create or update DynamoDB record.
     *
     * @param organization The Organization to be written
     */
    //I'm reading that best practice is to return an object from an update activity
    public Organization writeOrganzation(Organization organization) {
        mapper.save(organization);
        return organization;
    }

    /**
     * Removes the provided Organization from DynamoDB, if present.
     *
     * @param organization The Organization to be deleted
     */
    public void deleteOrganization(Organization organization) {
        mapper.delete(organization);
    }

}
