package com.nashss.se.musicplaylistservice.activity.taskActivities;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTasksForOrgRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTasksForOrgResult;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetTasksForOrgActivity for the Task endpoint.
 *
 * This API allows the customer to interact with Task objects in the database.
 */
public class GetTasksForOrgActivity {
    private final TaskDao taskDao;

    /**
     * Instantiates a new GetTasksForOrgActivity object.
     *
     * @param taskDao TaskDao to access the playlist table.
     */
    @Inject
    public GetTasksForOrgActivity(TaskDao taskDao) { this.taskDao = taskDao; }

    /**
     * This method handles the incoming request by retrieving list of tasks belong to an org from the database, if any exist.
     * <p>
     * It then returns the list.
     * <p>
     * If no tasks are found, the method will return an empty list.
     *
     * @param getTasksForOrgRequest request object containing the orgId
     * @return GetTasksForOrgResult result object containing the API defined {@link java.util.List<Task>}
     */

    public GetTasksForOrgResult handleRequest(final GetTasksForOrgRequest getTasksForOrgRequest) {
        List<Task> taskList = new ArrayList<>();
        taskList = taskDao.getTasksForOrg(getTasksForOrgRequest.getOrgId());
        return GetTasksForOrgResult.builder()
                .withTaskList(taskList)
                .build();
    }


}
