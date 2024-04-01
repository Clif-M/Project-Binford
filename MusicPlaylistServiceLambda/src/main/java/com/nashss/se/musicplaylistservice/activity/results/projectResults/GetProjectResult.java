package com.nashss.se.musicplaylistservice.activity.results.projectResults;

import com.nashss.se.musicplaylistservice.dynamodb.models.Project;

public class GetProjectResult {
    private final Project project;

    private GetProjectResult(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Project project;

        public Builder withProject(Project project) {
            this.project = project;
            return this;
        }

        public GetProjectResult build() {
            return new GetProjectResult(project);
        }
    }
}
