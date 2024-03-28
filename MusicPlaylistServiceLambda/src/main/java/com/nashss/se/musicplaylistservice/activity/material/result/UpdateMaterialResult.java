package com.nashss.se.musicplaylistservice.activity.material.result;

import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

public class UpdateMaterialResult {
    private final Material material;

    @Override
    public String toString() {
        return "UpdateMaterialResult{" +
                "material=" + material +
                '}';
    }

    private UpdateMaterialResult(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Material material;
        public Builder withMaterial(Material material){
            this.material = material;
            return this;
        }

        public UpdateMaterialResult build() {
            return new UpdateMaterialResult(material);
        }
    }
}
