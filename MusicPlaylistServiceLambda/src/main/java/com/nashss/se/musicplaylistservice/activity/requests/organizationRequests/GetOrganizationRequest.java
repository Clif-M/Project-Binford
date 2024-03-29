package com.nashss.se.musicplaylistservice.activity.requests.organizationRequests;


public class GetOrganizationRequest {
    private final String orgId;

    private GetOrganizationRequest(String orgId) {
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
        public GetOrganizationRequest build() {
            return new GetOrganizationRequest(orgId);
        }
    }
}
