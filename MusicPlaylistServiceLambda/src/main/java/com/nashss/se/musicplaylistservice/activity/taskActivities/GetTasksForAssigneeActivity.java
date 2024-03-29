package com.nashss.se.musicplaylistservice.activity.taskActivities;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTasksForAssigneeRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTasksForAssigneeResult;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the GetTasksForAssigneeActivity for the Task endpoint.
 *
 * This API allows the customer to interact with Task objects in the database.
 */
public class GetTasksForAssigneeActivity {
    private final TaskDao taskDao;

    /**
     * Instantiates a new GetTasksForAssigneeActivity object.
     *
     * @param taskDao TaskDao to access the playlist table.
     */
    @Inject
    public GetTasksForAssigneeActivity(TaskDao taskDao) { this.taskDao = taskDao; }

    /**
     * This method handles the incoming request by retrieving list of tasks belonging to an org and assignee from the database, if any exist.
     * <p>
     * It then returns the list.
     * <p>
     * If no tasks are found, the method will return an empty list.
     *
     * @param getTasksForAssigneeRequest request object containing the orgId
     * @return GetTasksForAssigneeResult result object containing the API defined {@link List<Task>}
     */

    public GetTasksForAssigneeResult handleRequest(final GetTasksForAssigneeRequest getTasksForAssigneeRequest) {
        List<Task> taskList = new ArrayList<>();
        taskList = taskDao.getTasksForAssignee(getTasksForAssigneeRequest.getOrgId(), getTasksForAssigneeRequest.getAssignee());
        return GetTasksForAssigneeResult.builder()
                .withTaskList(taskList)
                .build();
    }


}
