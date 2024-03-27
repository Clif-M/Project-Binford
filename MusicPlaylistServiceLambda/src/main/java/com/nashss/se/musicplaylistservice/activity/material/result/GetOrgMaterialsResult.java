package com.nashss.se.musicplaylistservice.activity.material.result;

import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

import java.util.List;

public class GetOrgMaterialsResult {
    private final List<Material> materials;

    private GetOrgMaterialsResult(List<Material> materials) {
        this.materials = materials;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public static Builder builder() {
        return new GetOrgMaterialsResult.Builder();
    }

    public static class Builder {
        private List<Material> materials;

        public Builder withMaterials(List<Material> materials) {
            this.materials = materials;
            return this;
        }

        public GetOrgMaterialsResult build() {
            return new GetOrgMaterialsResult(materials);
        }
    }
}
