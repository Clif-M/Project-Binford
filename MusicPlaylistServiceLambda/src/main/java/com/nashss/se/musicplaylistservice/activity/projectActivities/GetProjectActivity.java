package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetProjectResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of GetProjectActivity for Project Binford's GetProject API
 *
 * This API allows a consumer to retrieve a single Project object by orgId and projectId
 */
public class GetProjectActivity {
    private final ProjectDao projectDao;
    private final TaskDao taskDao;

    /**
     * Instantiates a new GetProjectActivity object
     *
     * @param projectDao ProjectDao to interact with the projects table
     */

    @Inject
    public GetProjectActivity(ProjectDao projectDao, TaskDao taskDao) {
            this.projectDao = projectDao;
            this.taskDao = taskDao;
        }

    /**
     * This method handles the request by retrieving a single project from the database
     * @param getProjectRequest request object containing an orgId and projectId
     * @return GetProjectResult object containing a single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Project}
     */
    public GetProjectResult handleRequest(final GetProjectRequest getProjectRequest) {
        Project result = projectDao.getSingleProject(getProjectRequest.getOrgId(), getProjectRequest.getProjectId());
        result.setCompletionPercentage(calculateCompletion(result.getTaskList()));
        return GetProjectResult.builder()
                .withProject(result)
                .build();
    }

    private double calculateCompletion(List<Task> taskList) {
        if (taskList == null || taskList.isEmpty()) {
            return 0;
        }
        double allTasks = 0;
        double completeTasks = 0;
        for(Task task : taskList) {
            allTasks++;
            if (taskDao.getSingleTask(task.getOrgId(), task.getTaskId()).getCompleted()) {
                completeTasks++;
            }
        }
        return completeTasks/allTasks;
    }
}

