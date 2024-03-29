package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.musicplaylistservice.exceptions.TaskNotFoundException;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Accesses data for a task using {@link Task} to interact with the model in DynamoDB.
 */

@Singleton
public class TaskDao {
    private final DynamoDBMapper mapper;

    /**
     * Instantiates a TaskDao object.
     *
     * @param mapper the {@link DynamoDBMapper} used to interact with the Task table
     */

    @Inject
    public TaskDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Retrieves a task by orgId and taskId.
     *
     * If not found, throws TaskNotFoundException.
     *
     * @param orgId The orgId to look up
     * @param taskId The taskId to look up
     * @return The corresponding Task if found
     */
    public Task getSingleTask(String orgId, String taskId) {
        Task task = mapper.load(Task.class, orgId, taskId);
        if (null == task) {
            throw new TaskNotFoundException(String.format("Could not find task with orgId %s and taskId %s", orgId, taskId));
        }
        return task;
    }

    /**
     * Retrieves all tasks matching provided orgId.
     *
     * If none found, returns an empty list.
     *
     * @param orgId The orgId to look up
     * @return A list of Tasks found, if any
     */
    public List<Task> getTasksForOrg(String orgId) {
        Task task = new Task();
        task.setOrgId(orgId);
        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withHashKeyValues(task);
        return mapper.query(Task.class, queryExpression);
    }

    /**
     * Saves provided Task to DynamoDB to create or update DynamoDB record.
     *
     * @param task The Task to be deleted
     */
    public void writeTask(Task task) {
        mapper.save(task);
    }

    /**
     * Removes the provided Task from DynamoDB, if present.
     *
     * @param task The Task to be deleted
     */
    public void deleteTask(Task task) {
        mapper.delete(task);
    }

    /**
     * Retrieves all tasks matching provided orgId and assignee.
     *
     * If none found, returns an empty list.
     *
     * @param orgId The orgId to look up
     * @param assignee the assignee to look up
     * @return A list of Tasks found, if any
     */
    public List<Task> getTasksForAssignee(String orgId, String assignee) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":orgId", new AttributeValue(orgId));
        valueMap.put(":assignee", new AttributeValue(assignee));
        DynamoDBQueryExpression<Task> queryExpression = new DynamoDBQueryExpression<Task>()
                .withIndexName("TasksSortByAssigneeIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("orgId = :orgId and assignee = :assignee")
                .withExpressionAttributeValues(valueMap);
        return mapper.query(Task.class, queryExpression);
    }

}
