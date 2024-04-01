package com.nashss.se.musicplaylistservice.activity.results.projectResults;

import com.nashss.se.musicplaylistservice.dynamodb.models.Project;

import java.util.List;

public class GetAllProjectsResult {
    private final List<Project> projectList;

    private GetAllProjectsResult(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Project> projectList;

        public Builder withProjectList(List<Project> projectList) {
            this.projectList = projectList;
            return this;
        }

        public GetAllProjectsResult build() {
            return new GetAllProjectsResult(projectList);
        }
    }
}