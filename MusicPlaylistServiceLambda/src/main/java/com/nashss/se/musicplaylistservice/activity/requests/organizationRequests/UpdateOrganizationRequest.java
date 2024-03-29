package com.nashss.se.musicplaylistservice.activity.requests.organizationRequests;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = UpdateOrganizationRequest.Builder.class)
public class UpdateOrganizationRequest {
    private final String orgId;
    private final String displayName;

    private UpdateOrganizationRequest(String orgId, String displayName) {
        this.orgId = orgId;
        this.displayName = displayName;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getDisplayName() { return displayName; }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String orgId;
        private String displayName;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            this.displayName = displayName;
            return this;
        }
        public UpdateOrganizationRequest build() {
            return new UpdateOrganizationRequest(orgId, displayName);
        }
    }
}
