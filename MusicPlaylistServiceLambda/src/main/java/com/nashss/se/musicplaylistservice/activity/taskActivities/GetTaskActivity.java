package com.nashss.se.musicplaylistservice.activity.taskActivities;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;

import javax.inject.Inject;

/**
 * Implementation of the GetTaskActivity for the Task endpoint.
 *
 * This API allows the customer to interact with Task objects in the database.
 */
public class GetTaskActivity {
    private final TaskDao taskDao;

    /**
     * Instantiates a new GetTaskActivity object.
     *
     * @param taskDao TaskDao to access the playlist table.
     */
    @Inject
    public GetTaskActivity(TaskDao taskDao) { this.taskDao = taskDao; }

    /**
     * This method handles the incoming request by retrieving a task from the database, if it exists.
     * <p>
     * It then returns the task.
     * <p>
     * If the task does not exist on the database, this method will propagate a TaskNotFoundException.
     *
     * @param getTaskRequest request object containing the orgId and taskId
     * @return GetTaskResult result object containing the API defined {@link com.nashss.se.musicplaylistservice.dynamodb.models.Task}
     */

    public GetTaskResult handleRequest(final GetTaskRequest getTaskRequest) {
        Task task = taskDao.getSingleTask(getTaskRequest.getOrgId(), getTaskRequest.getTaskId());
        return GetTaskResult.builder()
                .withTask(task)
                .build();
    }


}
