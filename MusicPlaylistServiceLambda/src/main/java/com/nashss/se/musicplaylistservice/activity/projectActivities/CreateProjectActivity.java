package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.CreateProjectResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.exceptions.ProjectNotFoundException;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Implementation of the CreateProjectActivity for the project endpoint.
 *
 * This API allows the customer to interact with project objects in the database.
 */
public class CreateProjectActivity {
    private final ProjectDao projectDao;

    /**
     * Instantiates a new CreateProjectActivity object.
     *
     * @param projectDao projectDao to access the playlist table.
     */
    @Inject
    public CreateProjectActivity(ProjectDao projectDao) { this.projectDao = projectDao; }

    /**
     * This method handles the incoming request by creating a new project with a random UUID.
     * <p>
     * It then returns the new project.
     * <p>
     * If orgId or  is null, this method will throw an InvalidAttributeException.
     *
     * @param createProjectRequest request object containing the orgId
     * @return CreateprojectResult result object containing the API defined {@link Project}
     */

    public CreateProjectResult handleRequest(final CreateProjectRequest createProjectRequest) {
        Project newProject = new Project();
        newProject.setOrgId(createProjectRequest.getOrgId());
        newProject.setProjectId(UUID.randomUUID().toString());
        while (projectIdExists(newProject)) {
            newProject.setProjectId(UUID.randomUUID().toString());
        }
        newProject.setName(createProjectRequest.getName());
        newProject.setProjectStatus(createProjectRequest.getProjectStatus());
        newProject.setTaskList(createProjectRequest.getTaskList());
        newProject.setCreationDate(createProjectRequest.getCreationDate());
        newProject.setEndDate(createProjectRequest.getEndDate());
        newProject.setProjectDescription(createProjectRequest.getProjectDescription());

        projectDao.writeProject(newProject);
        return CreateProjectResult.builder()
                .withproject(newProject)
                .build();
    }

    private boolean projectIdExists(Project project) {
        try {
            projectDao.getSingleProject(project.getOrgId(), project.getProjectId());
            return true;
        } catch (ProjectNotFoundException e) {
            return false;
        }
    }

}
