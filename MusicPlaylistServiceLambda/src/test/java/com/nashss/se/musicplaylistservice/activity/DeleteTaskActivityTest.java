package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.DeleteTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.DeleteTaskResult;
import com.nashss.se.musicplaylistservice.activity.taskActivities.DeleteTaskActivity;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteTaskActivityTest {
    @Mock
    private TaskDao taskDao;

    private DeleteTaskActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new DeleteTaskActivity(taskDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoDeleteMethod() {
        // GIVEN
        String orgId = "orgId";
        String taskId = "taskId";
        Task task = new Task();
        DeleteTaskRequest request = DeleteTaskRequest.builder()
                .withOrgId(orgId)
                .withTaskId(taskId)
                .build();
        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        // WHEN
        DeleteTaskResult result = activity.handleRequest(request);

        // THEN
        verify(taskDao).deleteTask(argumentCaptor.capture());
        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected method to call Dao with values matching those provided in request");
        assertEquals(taskId, argumentCaptor.getValue().getTaskId(), "Expected method to call Dao with values matching those provided in request");
        assertEquals(orgId, result.getTask().getOrgId(), "Expected method to output result with values matching those provided in request");
        assertEquals(taskId, result.getTask().getTaskId(), "Expected method to output result with values matching those provided in request");
    }
}