package com.nashss.se.musicplaylistservice.activity.results.taskResults;

import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

import java.util.List;

public class GetTasksForOrgResult {
    private final List<Task> tasks;

    private GetTasksForOrgResult(List<Task> tasks) { this.tasks = tasks; }

    public List<Task> getTasks() {
        return tasks;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private List<Task> tasks;

        public Builder withTaskList(List<Task> tasks) {
            this.tasks = tasks;
            return this;
        }

        public GetTasksForOrgResult build() { return new GetTasksForOrgResult(tasks); }
    }
}
