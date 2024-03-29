package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTasksForAssigneeRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTasksForAssigneeResult;
import com.nashss.se.musicplaylistservice.activity.taskActivities.GetTasksForAssigneeActivity;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetTasksForAssigneeActivityTest {
    @Mock
    private TaskDao taskDao;

    private GetTasksForAssigneeActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetTasksForAssigneeActivity(taskDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN
        String orgId = "orgId";
        String assignee = "assignee";
        GetTasksForAssigneeRequest request = GetTasksForAssigneeRequest.builder()
                .withOrgId(orgId)
                .withAssignee(assignee)
                .build();
        doReturn(new ArrayList<>()).when(taskDao).getTasksForAssignee(orgId, assignee);
        // WHEN
        GetTasksForAssigneeResult result = activity.handleRequest(request);

        // THEN
        verify(taskDao).getTasksForAssignee(orgId, assignee);
    }
}