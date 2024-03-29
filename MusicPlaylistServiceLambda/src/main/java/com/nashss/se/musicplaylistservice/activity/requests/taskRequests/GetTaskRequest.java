package com.nashss.se.musicplaylistservice.activity.requests.taskRequests;


public class GetTaskRequest {
    private final String orgId;
    private final String taskId;

    private GetTaskRequest(String orgId, String taskId) {
        this.orgId = orgId;
        this.taskId = taskId;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getTaskId() {
        return taskId;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    public static class Builder {
        private String orgId;
        private String taskId;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder withTaskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public GetTaskRequest build() { return new GetTaskRequest(orgId, taskId); }
    }
}
