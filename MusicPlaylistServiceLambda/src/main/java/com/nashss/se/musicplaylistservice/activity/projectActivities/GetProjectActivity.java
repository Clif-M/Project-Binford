package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetProjectResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;

import javax.inject.Inject;

/**
 * Implementation of GetProjectActivity for Project Binford's GetProject API
 *
 * This API allows a consumer to retrieve a single Project object by orgId and projectId
 */
public class GetProjectActivity {
    private final ProjectDao projectDao;

    /**
     * Instantiates a new GetProjectActivity object
     *
     * @param projectDao ProjectDao to interact with the projects table
     */

    @Inject
    public GetProjectActivity(ProjectDao projectDao) {
            this.projectDao = projectDao;
        }

    /**
     * This method handles the request by retrieving a single project from the database
     * @param getProjectRequest request object containing an orgId and projectId
     * @return GetProjectResult object containing a single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Project}
     */
    public GetProjectResult handleRequest(final GetProjectRequest getProjectRequest) {
        return GetProjectResult.builder()
                .withProject(projectDao.getSingleProject(getProjectRequest.getOrgId(), getProjectRequest.getProjectId()))
                .build();
    }
}

