package com.nashss.se.musicplaylistservice.activity.requests.taskRequests;


public class GetTasksForAssigneeRequest {
    private final String orgId;
    private final String assignee;

    private GetTasksForAssigneeRequest(String orgId, String assignee) {
        this.orgId = orgId;
        this.assignee = assignee;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getAssignee() {
        return assignee;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    public static class Builder {
        private String orgId;
        private String assignee;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder withAssignee(String assignee) {
            this.assignee = assignee;
            return this;
        }

        public GetTasksForAssigneeRequest build() { return new GetTasksForAssigneeRequest(orgId, assignee); }
    }
}
