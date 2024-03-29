package com.nashss.se.musicplaylistservice.activity.taskActivities;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.CreateTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.UpdateTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.CreateTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.UpdateTaskResult;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.exceptions.TaskNotFoundException;

import javax.inject.Inject;
import java.util.UUID;

/**
 * Implementation of the CreateTaskActivity for the Task endpoint.
 *
 * This API allows the customer to interact with Task objects in the database.
 */
public class CreateTaskActivity {
    private final TaskDao taskDao;

    /**
     * Instantiates a new CreateTaskActivity object.
     *
     * @param taskDao TaskDao to access the playlist table.
     */
    @Inject
    public CreateTaskActivity(TaskDao taskDao) { this.taskDao = taskDao; }

    /**
     * This method handles the incoming request by creating a new task with a random UUID.
     * <p>
     * It then returns the new task.
     * <p>
     * If either orgId is null, this method will throw an invalid attribute exception.
     *
     * @param createTaskRequest request object containing the orgId
     * @return CreateTaskResult result object containing the API defined {@link Task}
     */

    public CreateTaskResult handleRequest(final CreateTaskRequest createTaskRequest) {
        Task newTask = new Task();
        newTask.setOrgId(createTaskRequest.getOrgId());
        newTask.setTaskId(UUID.randomUUID().toString());
        while (taskIdExists(newTask)) {
            newTask.setTaskId(UUID.randomUUID().toString());
        }
        newTask.setAssignee(createTaskRequest.getAssignee());
        newTask.setCompleted(createTaskRequest.getCompleted());
        newTask.setHoursToComplete(createTaskRequest.getHoursToComplete());
        newTask.setMaterialsList(createTaskRequest.getMaterialsList());
        newTask.setName(createTaskRequest.getName());
        newTask.setStartTime(createTaskRequest.getStartTime());
        newTask.setStopTime(createTaskRequest.getStopTime());
        taskDao.writeTask(newTask);
        return CreateTaskResult.builder()
                .withTask(newTask)
                .build();
    }

    private boolean taskIdExists(Task task) {
        try {
            taskDao.getSingleTask(task.getOrgId(), task.getTaskId());
            return true;
        } catch (TaskNotFoundException e) {
            return false;
        }
    }

}
