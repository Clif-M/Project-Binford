package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.UpdateTaskRequest;
import com.nashss.se.musicplaylistservice.activity.taskActivities.UpdateTaskActivity;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateTaskActivityTest {
    @Mock
    private TaskDao taskDao;

    private UpdateTaskActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new UpdateTaskActivity(taskDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesTaskName() {
        // GIVEN
        String orgId = "orgId";
        String taskId = "taskId";
        String oldName = "oldName";
        String newName = "newName";

        UpdateTaskRequest request = UpdateTaskRequest.builder()
                .withOrgId(orgId)
                .withTaskId(taskId)
                .withName(newName)
                .build();

        Task start = new Task();
        start.setOrgId(orgId);
        start.setTaskId(taskId);
        start.setName(oldName);

        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);

        doReturn(start).when(taskDao).getSingleTask(orgId, taskId);

        // WHEN
        activity.handleRequest(request);
        verify(taskDao).writeTask(argumentCaptor.capture());

        // THEN
        verify(taskDao).getSingleTask(orgId, taskId);
        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected class to pass original orgId to DAO for write");
        assertEquals(newName, argumentCaptor.getValue().getName(), "Expected class to pass updated name to DAO for write");
    }
}
