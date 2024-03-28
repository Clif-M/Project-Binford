package com.nashss.se.musicplaylistservice.activity.material.request;

public class DeleteMaterialRequest {

    private final String orgId;
    private final String materialId;

    private DeleteMaterialRequest(String orgId, String materialId) {
        this.orgId = orgId;
        this.materialId = materialId;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public static Builder builder() {
       return new Builder();
    }
    public static class Builder {

        private String orgId;
        private String materialId;
        public Builder withOrgId(String orgId) {
            this.orgId=orgId;
            return this;
        }
        public Builder withMaterialId(String materialId) {
            this.materialId=materialId;
            return this;
        }

        public DeleteMaterialRequest build() {
            return new DeleteMaterialRequest(orgId,materialId);
        }

    }

}
