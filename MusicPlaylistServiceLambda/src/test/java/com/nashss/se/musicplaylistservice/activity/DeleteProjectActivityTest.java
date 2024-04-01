package com.nashss.se.musicplaylistservice.activity.projectActivities;

import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.DeleteProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.DeleteProjectResult;
import com.nashss.se.musicplaylistservice.activity.projectActivities.DeleteProjectActivity;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static javax.management.Query.times;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.MockitoAnnotations.openMocks;

public class DeleteProjectActivityTest {
    @Mock
    private TaskDao taskDao;
    @Mock
    private ProjectDao projectDao;
    List<Task> taskList;
    private DeleteProjectActivity activity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new DeleteProjectActivity(projectDao, taskDao);
    }

    @Test
    public void handleRequest_goodRequest_callsProjectDaoDeleteMethod() {
        // GIVEN
        String orgId = "orgId";
        String projectId = "projectId";

        Project project = new Project();
        project.setOrgId(orgId);
        project.setProjectId(projectId);

        DeleteProjectRequest request = DeleteProjectRequest.builder()
                .withOrgId(orgId)
                .withProjectId(projectId)
                .build();
        ArgumentCaptor<Project> argumentCaptor = ArgumentCaptor.forClass(Project.class);
        when(projectDao.getSingleProject(orgId, projectId)).thenReturn(project);
        // WHEN
        DeleteProjectResult result = activity.handleRequest(request);

        // THEN
        verify(projectDao).deleteProject(argumentCaptor.capture());
        assertEquals(orgId, argumentCaptor.getValue().getOrgId(), "Expected method to call Dao with values matching those provided in request");
        assertEquals(projectId, argumentCaptor.getValue().getProjectId(), "Expected method to call Dao with values matching those provided in request");

    }

    @Test
    public void handleRequest_goodRequest_callsTaskDaoDeleteMethod() {
        // GIVEN
        String orgId = "orgId";
        String projId = "projectId";
        Task task1 = new Task();
        task1.setOrgId("org55");
        task1.setTaskId("task55");
        task1.setName("rake leaves");
        Task task2 = new Task();
        task2.setOrgId("org55");
        task2.setTaskId("task56");
        task2.setName("mow lawn");
        List<Task> taskList = new ArrayList<>();
        taskList.add(task1);
       // taskList.add(task2);

        Project project = new Project();
        project.setOrgId(orgId);
        project.setProjectId(projId);
        project.setTaskList(taskList);
        DeleteProjectRequest request = DeleteProjectRequest.builder()
                .withOrgId(orgId)
                .withProjectId(projId)
                .build();

        ArgumentCaptor<Task> argumentCaptor = ArgumentCaptor.forClass(Task.class);
        when(projectDao.getSingleProject(orgId, projId)).thenReturn(project);

        // WHEN
        DeleteProjectResult result = activity.handleRequest(request);

        // THEN
        verify(taskDao).deleteTask(argumentCaptor.capture());
        assertEquals(task1.getTaskId(), argumentCaptor.getValue().getTaskId());
    }

}