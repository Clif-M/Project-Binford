package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetAllProjectsRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetAllProjectsResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of GetAllProjectsActivity for Project Binford's GetAllProjects API
 *
 * This API allows a consumer to retrieve a list of Project objects by orgId
 */
public class GetAllProjectsActivity {
    private final ProjectDao projectDao;
    private final TaskDao taskDao;

    /**
     * Instantiates a new GetAllProjectsActivity object
     *
     * @param projectDao ProjectDao to interact with the project table
     */

    @Inject
    public GetAllProjectsActivity(ProjectDao projectDao, TaskDao taskDao) {
        this.projectDao = projectDao;
        this.taskDao= taskDao;
    }

    /**
     * This method handles the request by retrieving all available projects from the database
     * @return GetAllProjectsResults object containing a list of {@link com.nashss.se.musicplaylistservice.dynamodb.models.Project}
     */
    public GetAllProjectsResult handleRequest(final GetAllProjectsRequest getAllProjectsRequest) {
        List<Project> projectList = projectDao.getAllProjects(getAllProjectsRequest.getOrgId());
        for (Project project : projectList) {
            project.setCompletionPercentage(this.calculateCompletion(project.getTaskList()));
        }
        return GetAllProjectsResult.builder()
                .withProjectList(projectList)
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