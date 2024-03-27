package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.nashss.se.musicplaylistservice.converters.MaterialListConverter;
import com.nashss.se.musicplaylistservice.converters.ZonedDateTimeConverter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "ProjectBinford_TaskTable")
public class Task {
    private String orgId;
    private String taskId;
    private String assignee;
    private Boolean completed;
    private Integer hoursToComplete;

    private List<Material> materialsList;
    private String name;
    private ZonedDateTime startTime;
    private ZonedDateTime stopTime;

    @DynamoDBHashKey(attributeName = "orgId")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @DynamoDBRangeKey(attributeName = "taskId")
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @DynamoDBAttribute(attributeName = "assignee")
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @DynamoDBAttribute(attributeName = "completed")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.B)
    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @DynamoDBAttribute(attributeName = "hoursToComplete")
    public Integer getHoursToComplete() {
        return hoursToComplete;
    }

    public void setHoursToComplete(Integer hoursToComplete) {
        this.hoursToComplete = hoursToComplete;
    }

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "startTime")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    @DynamoDBAttribute(attributeName = "stopTime")
    @DynamoDBTypeConverted(converter = ZonedDateTimeConverter.class)
    public ZonedDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(ZonedDateTime stopTime) {
        this.stopTime = stopTime;
    }

    @DynamoDBAttribute(attributeName = "materialsList")
    @DynamoDBTypeConverted(converter = MaterialListConverter.class)
    public List<Material> getMaterialsList() {
        return materialsList;
    }

    public void setMaterialsList(List<Material> materialsList) {
        this.materialsList = materialsList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.orgId + this.taskId);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) { return false; }
        if (this == o) { return true; }
        if (this.getClass() != o.getClass()) { return false; }
        Task other = (Task) o;
        return ( this.orgId.equals(other.orgId) && this.taskId.equals(other.taskId));
    }
}
