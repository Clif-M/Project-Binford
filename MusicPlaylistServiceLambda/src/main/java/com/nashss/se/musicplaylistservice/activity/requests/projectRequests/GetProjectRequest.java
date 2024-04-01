package com.nashss.se.musicplaylistservice.activity.requests.projectRequests;


public class GetProjectRequest {
    private final String orgId;
    private final String projectId;

    private GetProjectRequest(String orgId, String projectId) {
        this.orgId = orgId;
        this.projectId = projectId;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getProjectId() {
        return projectId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String orgId;
        private String projectId;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }
        public GetProjectRequest build() {
            return new GetProjectRequest(orgId, projectId);
        }
    }
}
