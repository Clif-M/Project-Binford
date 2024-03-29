package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.activity.taskActivities.GetTaskActivity;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetTaskActivityTest {
    @Mock
    private TaskDao taskDao;

    private GetTaskActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetTaskActivity(taskDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN
        String orgId = "orgId";
        String taskId = "taskId";
        Task task = new Task();
        task.setOrgId(orgId);
        task.setTaskId(taskId);
        GetTaskRequest request = GetTaskRequest.builder()
                .withOrgId(orgId)
                .withTaskId(taskId)
                .build();

        doReturn(task).when(taskDao).getSingleTask(orgId, taskId);

        // WHEN
        GetTaskResult result = activity.handleRequest(request);

        // THEN
        verify(taskDao).getSingleTask(orgId,taskId);
        assertEquals(orgId, result.getTask().getOrgId(), "Expected method to output result with values matching those provided in request");
        assertEquals(taskId, result.getTask().getTaskId(), "Expected method to output result with values matching those provided in request");
    }
}
