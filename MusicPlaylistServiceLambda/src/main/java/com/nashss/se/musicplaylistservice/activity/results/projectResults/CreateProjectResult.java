package com.nashss.se.musicplaylistservice.activity.results.projectResults;

import com.nashss.se.musicplaylistservice.dynamodb.models.Project;

public class CreateProjectResult {
    private final Project project;

    private CreateProjectResult(Project project) { this.project = project; }

    public Project getproject() {
        return project;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Project project;

        public Builder withproject(Project project) {
            this.project = project;
            return this;
        }

        public CreateProjectResult build() { return new CreateProjectResult(project); }
    }
}
