package com.nashss.se.musicplaylistservice.activity.requests.projectRequests;


public class GetAllProjectsRequest {
    private final String orgId;

    private GetAllProjectsRequest(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String orgId;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public GetAllProjectsRequest build() {
            return new GetAllProjectsRequest(orgId);
        }
    }
}

