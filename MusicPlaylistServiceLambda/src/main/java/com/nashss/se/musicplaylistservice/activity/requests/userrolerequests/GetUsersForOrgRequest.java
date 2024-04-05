package com.nashss.se.musicplaylistservice.activity.requests.userrolerequests;

public class GetUsersForOrgRequest {
    private final String orgId;

    private GetUsersForOrgRequest(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String orgId;

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public GetUsersForOrgRequest build() {
            return new GetUsersForOrgRequest(orgId);
        }
    }
}
