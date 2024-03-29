package com.nashss.se.musicplaylistservice.activity.taskActivities;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.DeleteTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.DeleteTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

import javax.inject.Inject;

/**
 * Implementation of the DeleteTaskActivity for the Task endpoint.
 *
 * This API allows the customer to interact with Task objects in the database.
 */
public class DeleteTaskActivity {
    private final TaskDao taskDao;

    /**
     * Instantiates a new DeleteTaskActivity object.
     *
     * @param taskDao TaskDao to access the playlist table.
     */
    @Inject
    public DeleteTaskActivity(TaskDao taskDao) { this.taskDao = taskDao; }

    /**
     * This method handles the incoming request by deleting a task from the database, if it exists.
     * <p>
     * It then returns the deleted task.
     *
     * @param deleteTaskRequest request object containing the orgId and taskId
     * @return GetTaskResult result object containing the API defined {@link Task}
     */

    public DeleteTaskResult handleRequest(final DeleteTaskRequest deleteTaskRequest) {

        Task task = new Task();
        task.setOrgId(deleteTaskRequest.getOrgId());
        task.setTaskId(deleteTaskRequest.getTaskId());
        taskDao.deleteTask(task);
        return DeleteTaskResult.builder()
                .withTask(task)
                .build();
    }


}
