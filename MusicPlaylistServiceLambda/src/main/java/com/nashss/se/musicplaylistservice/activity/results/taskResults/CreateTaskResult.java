package com.nashss.se.musicplaylistservice.activity.results.taskResults;

import com.nashss.se.musicplaylistservice.dynamodb.models.Task;

public class CreateTaskResult {
    private final Task task;

    private CreateTaskResult(Task task) { this.task = task; }

    public Task getTask() {
        return task;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Task task;

        public Builder withTask(Task task) {
            this.task = task;
            return this;
        }

        public CreateTaskResult build() { return new CreateTaskResult(task); }
    }
}
