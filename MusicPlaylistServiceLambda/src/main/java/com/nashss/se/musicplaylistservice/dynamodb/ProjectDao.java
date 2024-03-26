package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.musicplaylistservice.exceptions.ProjectNotFoundException;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Accesses data for a project using {@link Project} to interact with the model in DynamoDB.
 */

@Singleton
public class ProjectDao {
    private final DynamoDBMapper mapper;

    /**
     * Instantiates a ProjectDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the Projects table
     */

    @Inject
    public ProjectDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves a project by orgId and projectId.
     * <p>
     * If not found, throws ProjectNotFoundException.
     *
     * @param orgId     The orgId to look up
     * @param projectId The projectId to look up
     * @return The corresponding Project if found
     */
    public Project getSingleProject(String orgId, String projectId) {
        Project project = mapper.load(Project.class, orgId, projectId);
        if (project == null) {
            throw new ProjectNotFoundException(String.format("Could not find project with orgId %s and projectId %s", orgId, projectId));
        }
        return project;
    }

    /**
     * Retrieves all projects matching provided orgId.
     * <p>
     * If none found, returns an empty list.
     *
     * @param orgId The orgId to look up
     * @return A list of Projects found, if any
     */
    public List<Project> getProjectsForOrg(String orgId) {
        Project project = new Project();
        project.setOrgId(orgId);
        DynamoDBQueryExpression<Project> queryExpression = new DynamoDBQueryExpression<Project>()
                .withHashKeyValues(project);
        return mapper.query(Project.class, queryExpression);
    }

    /**
     * Saves provided Project to DynamoDB to create or update DynamoDB record.
     *
     * @param project The Project to be saved
     */
    public void writeProject(Project project) {
        mapper.save(project);
    }

    /**
     * Removes the provided Project from DynamoDB, if present.
     *
     * @param project The Project to be deleted
     */
    public void deleteProject(Project project) {
        mapper.delete(project);
    }
}