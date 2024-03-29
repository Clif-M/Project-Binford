package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import com.nashss.se.musicplaylistservice.exceptions.TaskNotFoundException;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
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

public class TaskDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private PaginatedQueryList<Task> paginatedQueryList;
    private TaskDao taskDao;
    private Task testTask;


    @BeforeEach
    public void setup() {
        initMocks(this);
        taskDao = new TaskDao(mapper);
        testTask = new Task();
        testTask.setOrgId("testOrg");
        testTask.setTaskId("testTaskId");
    }

    @Test
    public void getSingleTask_MatchingTask_returnsTask() {
        //GIVEN
        doReturn(testTask).when(mapper).load(Task.class, testTask.getOrgId(), testTask.getTaskId());

        //WHEN
        Task result = taskDao.getSingleTask(testTask.getOrgId(), testTask.getTaskId());

        //THEN
        assertEquals(testTask.getTaskId(), result.getTaskId(), "Expected method to pass back object with matching values");
        assertEquals(testTask.getOrgId(), result.getOrgId(), "Expected method to pass back object with matching values");
    }

    @Test
    public void getSingleTask_noMatchingTask_ThrowsTaskNotFoundException() {
        //GIVEN
        doReturn(null).when(mapper).load(Task.class, testTask.getOrgId(), testTask.getTaskId());

        //WHEN
        //THEN
        assertThrows(TaskNotFoundException.class, () -> taskDao.getSingleTask(testTask.getOrgId(), testTask.getTaskId()), "Expected method to throw error");
    }

    @Test
    public void getTasksForOrg_anyTask_returnsList() {
        //GIVEN
        ArgumentCaptor<DynamoDBQueryExpression<Task>> argumentCaptor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        doReturn(paginatedQueryList).when(mapper).query(eq(Task.class),any(DynamoDBQueryExpression.class));
        //WHEN
        taskDao.getTasksForOrg(testTask.getOrgId());
        verify(mapper).query(eq(Task.class),argumentCaptor.capture());

        //THEN
        assertEquals(testTask.getOrgId(), argumentCaptor.getValue().getHashKeyValues().getOrgId(), "Expected method to faithfully pass provided values to mapper");

    }

    @Test
    public void writeTask_anyTask_interactsWithSaveMethod() {
        //GIVEN
        //WHEN
        taskDao.writeTask(testTask);

        //THEN
        verify(mapper).save(testTask);
    }

    @Test
    public void deleteTask_anyTask_interactsWithDeleteMethod() {
        //GIVEN
        //WHEN
        taskDao.deleteTask(testTask);

        //THEN
        verify(mapper).delete(testTask);
    }
}
