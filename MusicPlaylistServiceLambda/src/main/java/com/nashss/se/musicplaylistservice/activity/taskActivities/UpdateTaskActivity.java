package com.nashss.se.musicplaylistservice.activity.taskActivities;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.UpdateTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.UpdateTaskResult;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.exceptions.TaskNotFoundException;

import javax.inject.Inject;

/**
 * Implementation of the UpdateTaskActivity for the Task endpoint.
 *
 * This API allows the customer to interact with Task objects in the database.
 */
public class UpdateTaskActivity {
    private final TaskDao taskDao;

    /**
     * Instantiates a new GetTaskActivity object.
     *
     * @param taskDao TaskDao to access the playlist table.
     */
    @Inject
    public UpdateTaskActivity(TaskDao taskDao) { this.taskDao = taskDao; }

    /**
     * This method handles the incoming request by checking to see if an existing task exists, then replacing it with the provided new content.
     * <p>
     * It then returns the new task.
     * <p>
     * If the task does not exist on the database, this method will propagate a TaskNotFoundException.
     * If either orgId or taskId is null, this method will throw an invalid attribute exception.
     *
     * @param updateTaskRequest request object containing the orgId and taskId
     * @return UpdateTaskResult result object containing the API defined {@link Task}
     */

    public UpdateTaskResult handleRequest(final UpdateTaskRequest updateTaskRequest) {
        Task updatedtask = taskDao.getSingleTask(updateTaskRequest.getOrgId(), updateTaskRequest.getTaskId());
        updatedtask.setAssignee(updateTaskRequest.getAssignee());
        updatedtask.setCompleted(updateTaskRequest.getCompleted());
        updatedtask.setHoursToComplete(updateTaskRequest.getHoursToComplete());
        updatedtask.setMaterialsList(updateTaskRequest.getMaterialsList());
        updatedtask.setName(updateTaskRequest.getName());
        updatedtask.setStartTime(updateTaskRequest.getStartTime());
        updatedtask.setStopTime(updateTaskRequest.getStopTime());
        taskDao.writeTask(updatedtask);
        return UpdateTaskResult.builder()
                .withTask(updatedtask)
                .build();
    }


}
