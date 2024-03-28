package com.nashss.se.musicplaylistservice.activity.requests.organizationRequests;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = CreateOrganizationRequest.Builder.class)
public class CreateOrganizationRequest {
    private final String orgId;
    private final String displayName;

    private CreateOrganizationRequest(String orgId, String displayName) {
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
        public CreateOrganizationRequest build() {
            return new CreateOrganizationRequest(orgId, displayName);
        }
    }
}
