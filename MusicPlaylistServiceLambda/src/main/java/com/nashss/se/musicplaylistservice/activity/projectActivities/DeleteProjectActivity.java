package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.DeleteProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.DeleteProjectResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the DeleteProjectActivity for the Project endpoint.
 *
 * This API allows the customer to interact with Project objects in the database.
 */
public class DeleteProjectActivity {
    private final ProjectDao projectDao;
    private final TaskDao taskDao;

    /**
     * Instantiates a new DeleteProjectActivity object.
     *
     * @param projectDao ProjectDao to access the projects table.
     * @param taskDao TaskDao to access the projects table.
     */
    @Inject
    public DeleteProjectActivity(ProjectDao projectDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.taskDao = taskDao;
     }

    /**
     * This method handles the incoming request by deleting each task in a project's task list
     * and then deleting the project from the database.
     * <p>
     * It then returns the deleted project.
     *
     * @param deleteProjectRequest request object containing the orgId and projectId
     * @return GetProjectResult result object containing the API defined {@link Project}
     */

    public DeleteProjectResult handleRequest(final DeleteProjectRequest deleteProjectRequest) {

        Project project = projectDao.getSingleProject(deleteProjectRequest.getOrgId(), deleteProjectRequest.getProjectId());

        List<Task> taskList  = project.getTaskList();
        if (taskList != null) {
            for (Task task : taskList) {
                taskDao.deleteTask(task);
            }
        }

        projectDao.deleteProject(project);
        return DeleteProjectResult.builder()
                .withProject(project)
                .build();
    }
}
