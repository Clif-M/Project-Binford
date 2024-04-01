package com.nashss.se.musicplaylistservice.activity.requests.projectRequests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;



import java.time.ZonedDateTime;
import java.util.List;

@JsonDeserialize(builder = CreateProjectRequest.Builder.class)
public class CreateProjectRequest {
    private String orgId;
    private String projectId;
    private String name;
    private List<Task> taskList;
    private Double completionPercentage;

    private String projectStatus;

    private ZonedDateTime creationDate;
    private ZonedDateTime endDate;
    private String projectDescription;

    private CreateProjectRequest(String orgId, String projectId, String name, List<Task> taskList, Double completionPercentage,
                                 String projectStatus, ZonedDateTime creationDate, ZonedDateTime endDate, String projectDescription) {

        this.orgId = orgId;
        this.projectId = projectId;
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

    public String getProjectId() {
        return projectId;
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
    public static com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder builder() {
        return new com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String orgId;
        private String projectId;
        private String name;
        private List<Task> taskList;
        private Double completionPercentage;

        private String projectStatus;

        private ZonedDateTime creationDate;
        private ZonedDateTime endDate;
        private String projectDescription;

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withName(String name) {
            this.name = name;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withTaskList(List<Task> taskList) {
            this.taskList = taskList;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withCompletionPercentage(Double completionPercentage) {
            this.completionPercentage = completionPercentage;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withProjectStatus(String projectStatus) {
            this.projectStatus = projectStatus;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withCreationDate(ZonedDateTime creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withEndDate(ZonedDateTime endDate) {
            this.endDate = endDate;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest.Builder withProjectDesciption(String projectDescription) {
            this.projectDescription = projectDescription;
            return this;
        }

        public com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest build() {
            return new com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest(orgId, projectId, name, taskList, completionPercentage,
                    projectStatus, creationDate, endDate, projectDescription);
        }
    }
}