package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.CreateTaskRequest;
import com.nashss.se.musicplaylistservice.activity.taskActivities.CreateTaskActivity;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateTaskActivityTest {
    @Mock
    private TaskDao taskDao;

    private CreateTaskActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new CreateTaskActivity(taskDao);
    }

    @Test
    public void handleRequest_goodRequest_returnsNewOrganization() {
        // GIVEN
        String orgId = "testId";
        String testName = "testName";


        CreateTaskRequest request = CreateTaskRequest.builder()
                .withOrgId(orgId)
                .withName(testName)
                .build();

        Task task = new Task();
        task.setOrgId(orgId);
        task.setName(testName);

        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);

        doThrow(new TaskNotFoundException()).when(taskDao).getSingleTask(any(String.class), any(String.class));

        // WHEN
        activity.handleRequest(request);
        verify(taskDao).writeTask(argumentCaptor.capture());

        // THEN
        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected class to pass provided orgId to DAO for write");
        assertEquals(testName, argumentCaptor.getValue().getName(), "Expected class to pass provided displayName to DAO for write");
    }


}