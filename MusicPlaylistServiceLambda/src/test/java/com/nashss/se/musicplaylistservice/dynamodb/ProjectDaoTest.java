package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.nashss.se.musicplaylistservice.exceptions.ProjectNotFoundException;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProjectDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private PaginatedQueryList<Project> paginatedQueryList;
    private ProjectDao projectDao;
    private Project testProject;


    @BeforeEach
    public void setup() {
        initMocks(this);
        projectDao = new ProjectDao(mapper);
        testProject = new Project();
        testProject.setOrgId("testOrg");
        testProject.setProjectId("testProjectId");
    }

    @Test
    public void getSingleProject_MatchingProject_returnsProject() {
        //GIVEN
        doReturn(testProject).when(mapper).load(Project.class, testProject.getOrgId(), testProject.getProjectId());

        //WHEN
        Project result = projectDao.getSingleProject(testProject.getOrgId(), testProject.getProjectId());

        //THEN
        assertEquals(testProject.getProjectId(), result.getProjectId(), "Expected method to pass back object with matching values");
        assertEquals(testProject.getOrgId(), result.getOrgId(), "Expected method to pass back object with matching values");
    }

    @Test
    public void getSingleProject_noMatchingProject_ThrowsProjectNotFoundException() {
        //GIVEN
        doReturn(null).when(mapper).load(Project.class, testProject.getOrgId(), testProject.getProjectId());

        //WHEN
        //THEN
        assertThrows(ProjectNotFoundException.class, () -> projectDao.getSingleProject(testProject.getOrgId(), testProject.getProjectId()),
                "Expected method to throw error");
    }

    @Test
    public void getProjectsForOrg_anyOrg_returnsList() {
        //GIVEN
        ArgumentCaptor<DynamoDBQueryExpression<Project>> argumentCaptor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        doReturn(paginatedQueryList).when(mapper).query(eq(Project.class),any(DynamoDBQueryExpression.class));
        //WHEN
        projectDao.getAllProjects(testProject.getOrgId());
        verify(mapper).query(eq(Project.class),argumentCaptor.capture());

        //THEN
        assertEquals(testProject.getOrgId(), argumentCaptor.getValue().getHashKeyValues().getOrgId(), "Expected method to pass provided values to mapper");

    }

    @Test
    public void writeProject_anyProject_interactsWithSaveMethod() {
        //GIVEN
        //WHEN
        projectDao.writeProject(testProject);
        //THEN
        verify(mapper).save(testProject);
    }

    @Test
    public void deleteProject_anyProject_interactsWithDeleteMethod() {
        //GIVEN
        //WHEN
        projectDao.deleteProject(testProject);

        //THEN
        verify(mapper).delete(testProject);
    }
}

