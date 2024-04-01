package com.nashss.se.musicplaylistservice.activity.requests.userrolerequests;

public class GetSingleUserRoleRequest {
    private final String userEmail;
    private final String orgId;

    private GetSingleUserRoleRequest(String orgId, String userEmail) {
        this.orgId = orgId;
        this.userEmail = userEmail;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userEmail;
        private String orgId;

        public Builder withEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }
        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public GetSingleUserRoleRequest build() {
            return new GetSingleUserRoleRequest(orgId, userEmail);
        }
    }
}
