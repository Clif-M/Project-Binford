package com.nashss.se.musicplaylistservice.activity.material.request;

public class GetOrgMaterialsRequest {

    private final String orgId;

    private GetOrgMaterialsRequest(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private String orgId;

        public Builder withOrgId(String orgId) {
            this.orgId=orgId;
            return this;
        }


        public GetOrgMaterialsRequest build() {
            return new GetOrgMaterialsRequest(orgId);
        }

    }
}
