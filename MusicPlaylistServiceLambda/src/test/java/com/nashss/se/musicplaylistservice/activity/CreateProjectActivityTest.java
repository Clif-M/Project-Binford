package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest;
import com.nashss.se.musicplaylistservice.activity.projectActivities.CreateProjectActivity;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.exceptions.ProjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateProjectActivityTest {
    @Mock
    private ProjectDao projectDao;

    private CreateProjectActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new CreateProjectActivity(projectDao);
    }

    @Test
    public void handleRequest_goodRequest_returnsNewProject() {
        // GIVEN
        String orgId = "testId";
        String testName = "testName";


        CreateProjectRequest request = CreateProjectRequest.builder()
                .withOrgId(orgId)
                .withName(testName)
                .build();

        Project project = new Project();
        project.setOrgId(orgId);
        project.setName(testName);

        ArgumentCaptor<Project> argumentCaptor = ArgumentCaptor.forClass(Project.class);

        doThrow(new ProjectNotFoundException()).when(projectDao).getSingleProject(any(String.class), any(String.class));

        // WHEN
        activity.handleRequest(request);
        verify(projectDao).writeProject(argumentCaptor.capture());

        // THEN
        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected class to pass provided orgId to DAO for write");
        assertEquals(testName, argumentCaptor.getValue().getName(), "Expected class to pass provided displayName to DAO for write");
    }

}
