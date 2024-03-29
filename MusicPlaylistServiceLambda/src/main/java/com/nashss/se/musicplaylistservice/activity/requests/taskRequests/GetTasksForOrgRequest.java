package com.nashss.se.musicplaylistservice.activity.requests.taskRequests;


public class GetTasksForOrgRequest {
    private final String orgId;

    private GetTasksForOrgRequest(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() { return new Builder(); }

    public static class Builder {
        private String orgId;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public GetTasksForOrgRequest build() { return new GetTasksForOrgRequest(orgId); }
    }
}
