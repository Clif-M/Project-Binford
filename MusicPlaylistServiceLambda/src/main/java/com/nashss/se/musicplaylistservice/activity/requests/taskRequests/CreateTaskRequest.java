package com.nashss.se.musicplaylistservice.activity.requests.taskRequests;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

import java.time.ZonedDateTime;
import java.util.List;

@JsonDeserialize(builder = CreateTaskRequest.Builder.class)
public class CreateTaskRequest {
    private String orgId;
    private String assignee;
    private Boolean completed;
    private Integer hoursToComplete;

    private List<Material> materialsList;
    private String name;
    private ZonedDateTime startTime;
    private ZonedDateTime stopTime;

    private CreateTaskRequest(String orgId, String assignee, Boolean completed, Integer hoursToComplete, List<Material> materialsList, String name, ZonedDateTime startTime, ZonedDateTime stopTime) {
        this.orgId = orgId;
        this.assignee = assignee;
        this.completed = completed;
        this.hoursToComplete = hoursToComplete;
        this.materialsList = materialsList;
        this.name = name;
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getAssignee() { return assignee; }

    public Boolean getCompleted() { return completed; }

    public Integer getHoursToComplete() { return hoursToComplete; }

    public List<Material> getMaterialsList() { return  materialsList; }

    public String getName() { return name; }

    public ZonedDateTime getStartTime() { return startTime; }

    public ZonedDateTime getStopTime() { return stopTime; }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    @JsonPOJOBuilder
    public static class Builder {
        private String orgId;
        private String assignee;
        private Boolean completed;
        private Integer hoursToComplete;

        private List<Material> materialsList;
        private String name;
        private ZonedDateTime startTime;
        private ZonedDateTime stopTime;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder withAssignee(String assignee) {
            this.assignee = assignee;
            return this;
        }

        public Builder withCompleted(Boolean completed) {
            this.completed = completed;
            return this;
        }

        public Builder withHoursToComplete(Integer hoursToComplete) {
            this.hoursToComplete = hoursToComplete;
            return  this;
        }

        public Builder withMaterialsList(List<Material> materialsList) {
            this.materialsList = materialsList;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withStartTime(ZonedDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withStopTime(ZonedDateTime stopTime) {
            this.stopTime = stopTime;
            return this;
        }

        public CreateTaskRequest build() { return new CreateTaskRequest(orgId, assignee, completed, hoursToComplete, materialsList, name, startTime, stopTime); }
    }
}
