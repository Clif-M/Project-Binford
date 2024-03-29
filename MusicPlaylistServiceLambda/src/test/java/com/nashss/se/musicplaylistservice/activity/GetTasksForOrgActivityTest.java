package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTasksForOrgRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTasksForOrgResult;
import com.nashss.se.musicplaylistservice.activity.taskActivities.GetTasksForOrgActivity;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetTasksForOrgActivityTest {
    @Mock
    private TaskDao taskDao;

    private GetTasksForOrgActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetTasksForOrgActivity(taskDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN
        String orgId = "orgId";
        GetTasksForOrgRequest request = GetTasksForOrgRequest.builder()
                .withOrgId(orgId)
                .build();
        doReturn(new ArrayList<>()).when(taskDao).getTasksForOrg(orgId);
        // WHEN
        GetTasksForOrgResult result = activity.handleRequest(request);

        // THEN
        verify(taskDao).getTasksForOrg(orgId);
    }
}