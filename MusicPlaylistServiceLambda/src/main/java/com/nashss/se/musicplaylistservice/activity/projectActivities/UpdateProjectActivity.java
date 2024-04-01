package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetProjectRequest;
import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.UpdateProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetProjectResult;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.UpdateProjectResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.exceptions.ProjectNotFoundException;

import javax.inject.Inject;

/**
 * Implementation of the UpdateProjectActivity for the Project endpoint.
 *
 * This API allows the customer to interact with Project objects in the database.
 */
public class UpdateProjectActivity {
    private final ProjectDao projectDao;

    /**
     * Instantiates a new GetProjectActivity object.
     *
     * @param projectDao ProjectDao to access the Projects table.
     */
    @Inject
    public UpdateProjectActivity(ProjectDao projectDao) { this.projectDao = projectDao; }

    /**
     * This method handles the incoming request by checking to see if an existing project exists, then replacing it with the provided new content.
     * <p>
     * It then returns the new project.
     * <p>
     * If the project does not exist in the database, this method will propagate a ProjectNotFoundException.
     * If either orgId or projectId is null, this method will throw an InvalidAttributeException.
     *
     * @param updateProjectRequest request object containing the orgId and projectId
     * @return UpdateProjectResult result object containing the API defined {@link Project}
     */

    public UpdateProjectResult handleRequest(final UpdateProjectRequest updateProjectRequest) {
        Project updatedproject = projectDao.getSingleProject(updateProjectRequest.getOrgId(), updateProjectRequest.getProjectId());
        updatedproject.setName(updateProjectRequest.getName());
        updatedproject.setTaskList(updateProjectRequest.getTaskList());
        updatedproject.setCompletionPercentage(updateProjectRequest.getCompletionPercentage());
        updatedproject.setProjectStatus(updateProjectRequest.getProjectStatus());
        updatedproject.setCreationDate(updateProjectRequest.getCreationDate());
        updatedproject.setEndDate(updateProjectRequest.getEndDate());
        updatedproject.setProjectDescription(updateProjectRequest.getProjectDescription());

        projectDao.writeProject(updatedproject);
        return UpdateProjectResult.builder()
                .withProject(updatedproject)
                .build();
    }
}

