package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetAllProjectsRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetAllProjectsResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetAllProjectsActivityTest {
    @Mock
    private ProjectDao projectDao;

    private GetAllProjectsActivity activity;
    private Project controlProject;
    private String someString = "1234";

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetAllProjectsActivity(projectDao);
        controlProject = new Project();
        controlProject.setOrgId(someString);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoGetMethod() {
        // GIVEN

        String orgId = "1";
        GetAllProjectsRequest request = GetAllProjectsRequest.builder()
                .withOrgId(orgId)
                .build();

        doReturn(new ArrayList<>()).when(projectDao).getAllProjects(orgId);

        // WHEN
        activity.handleRequest(request);

        // THEN
        verify(projectDao).getAllProjects(orgId);
    }

    @Test
    void handleRequest_validRequest_returnsListProjects() {
        //GIVEN
        Project controlProject2 = new Project();
        controlProject2.setProjectId(someString + "f");
        GetAllProjectsRequest request = GetAllProjectsRequest.builder()
                .withOrgId(someString)
                .build();
        List<Project> projList = new ArrayList<>();
        projList.add(controlProject);
        projList.add(controlProject2);
        when(projectDao.getAllProjects(request.getOrgId())).thenReturn(projList);
        //WHEN
        GetAllProjectsResult result = activity.handleRequest(request);
        //THEN
        assertTrue(result.getProjectList().size() == 2);
        verify(projectDao, times(1)).getAllProjects(someString);
    }
}

//    @Test
//    public void handleRequest_nullOrgId_throwsException() {
//        //GIVEN
//        GetAllProjectsRequest request = GetAllProjectsRequest.builder()
//                .withOrgId(null)
//                .build();
//        //WHEN
//        //THEN
//        assertThrows(InvalidAttributeValueException.class, () -> {
//            activity.handleRequest(request);
//        }, "Expected method to throw error when provided null value");
//    }