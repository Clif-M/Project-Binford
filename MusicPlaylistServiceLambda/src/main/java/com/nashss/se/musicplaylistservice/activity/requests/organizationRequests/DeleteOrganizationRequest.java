package com.nashss.se.musicplaylistservice.activity.requests.organizationRequests;


public class DeleteOrganizationRequest {
    private final String orgId;

    private DeleteOrganizationRequest(String orgId) {
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
        public DeleteOrganizationRequest build() {
            return new DeleteOrganizationRequest(orgId);
        }
    }
}
