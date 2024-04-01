package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetAllProjectsRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetAllProjectsResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;

import javax.inject.Inject;

/**
 * Implementation of GetAllProjectsActivity for Project Binford's GetAllProjects API
 *
 * This API allows a consumer to retrieve a list of Project objects by orgId
 */
public class GetAllProjectsActivity {
    private final ProjectDao projectDao;

    /**
     * Instantiates a new GetAllProjectsActivity object
     *
     * @param projectDao ProjectDao to interact with the project table
     */

    @Inject
    public GetAllProjectsActivity(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    /**
     * This method handles the request by retrieving all available projects from the database
     * @return GetAllProjectsResults object containing a list of {@link com.nashss.se.musicplaylistservice.dynamodb.models.Project}
     */
    public GetAllProjectsResult handleRequest(final GetAllProjectsRequest getAllProjectsRequest) {
        return GetAllProjectsResult.builder()
                .withProjectList(projectDao.getAllProjects(getAllProjectsRequest.getOrgId()))
                .build();
    }
}