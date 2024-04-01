package com.nashss.se.musicplaylistservice.activity.results.projectResults;

import com.nashss.se.musicplaylistservice.dynamodb.models.Project;

public class UpdateProjectResult {
    private final Project project;

    private UpdateProjectResult(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Project project;

        public Builder withProject(Project project) {
            this.project = project;
            return this;
        }

        public UpdateProjectResult build() { return new UpdateProjectResult(project); }
    }
}
