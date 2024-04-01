package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.UpdateProjectRequest;
import com.nashss.se.musicplaylistservice.activity.projectActivities.UpdateProjectActivity;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateProjectActivityTest {
    @Mock
    private ProjectDao projectDao;

    private UpdateProjectActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new UpdateProjectActivity(projectDao);
    }

    @Test
    public void handleRequest_goodRequest_updatesProjectName() {
        // GIVEN
        String orgId = "orgId";
        String projectId = "projectId";
        String oldName = "oldName";
        String newName = "newName";

        UpdateProjectRequest request = UpdateProjectRequest.builder()
                .withOrgId(orgId)
                .withProjectId(projectId)
                .withName(newName)
                .build();

        Project start = new Project();
        start.setOrgId(orgId);
        start.setProjectId(projectId);
        start.setName(oldName);

        ArgumentCaptor<Project> argumentCaptor = ArgumentCaptor.forClass(Project.class);

        doReturn(start).when(projectDao).getSingleProject(orgId, projectId);

        // WHEN
        activity.handleRequest(request);
        verify(projectDao).writeProject(argumentCaptor.capture());

        // THEN
        verify(projectDao).getSingleProject(orgId, projectId);
        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected class to pass original orgId to DAO for write");
        assertEquals(newName, argumentCaptor.getValue().getName(), "Expected class to pass updated name to DAO for write");
    }
}