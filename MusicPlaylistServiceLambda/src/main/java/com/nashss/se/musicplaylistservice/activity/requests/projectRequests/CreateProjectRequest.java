package com.nashss.se.musicplaylistservice.activity.requests.projectRequests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;



import java.time.ZonedDateTime;
import java.util.List;

@JsonDeserialize(builder = CreateProjectRequest.Builder.class)
public class CreateProjectRequest {
    private String orgId;
    private String name;
    private List<Task> taskList;
    private Double completionPercentage;

    private String projectStatus;

    private ZonedDateTime creationDate;
    private ZonedDateTime endDate;
    private String projectDescription;

    private CreateProjectRequest(String orgId, String name, List<Task> taskList, Double completionPercentage,
                                 String projectStatus, ZonedDateTime creationDate, ZonedDateTime endDate, String projectDescription) {

        this.orgId = orgId;
        this.name = name;
        this.taskList = taskList;
        this.completionPercentage = completionPercentage;
        this.projectStatus = projectStatus;
        this.creationDate = creationDate;
        this.endDate = endDate;
        this.projectDescription = projectDescription;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Double getCompletionPercentage() {
        return completionPercentage;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String orgId;
        private String name;
        private List<Task> taskList;
        private Double completionPercentage;

        private String projectStatus;

        private ZonedDateTime creationDate;
        private ZonedDateTime endDate;
        private String projectDescription;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withTaskList(List<Task> taskList) {
            this.taskList = taskList;
            return this;
        }

        public Builder withCompletionPercentage(Double completionPercentage) {
            this.completionPercentage = completionPercentage;
            return this;
        }

        public Builder withProjectStatus(String projectStatus) {
            this.projectStatus = projectStatus;
            return this;
        }

        public Builder withCreationDate(ZonedDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Builder withEndDate(ZonedDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder withProjectDescription(String projectDescription) {
            this.projectDescription = projectDescription;
            return this;
        }

        public CreateProjectRequest build() {
            return new CreateProjectRequest(orgId, name, taskList, completionPercentage,
                    projectStatus, creationDate, endDate, projectDescription);
        }
    }
}