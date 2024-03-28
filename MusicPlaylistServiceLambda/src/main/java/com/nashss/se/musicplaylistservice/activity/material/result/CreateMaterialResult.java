package com.nashss.se.musicplaylistservice.activity.material.result;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.nashss.se.musicplaylistservice.activity.material.request.CreateMaterialRequest;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

public class CreateMaterialResult {
    private final Material material;

    @Override
    public String toString() {
        return "CreateMaterialResult{" +
                "material=" + material +
                '}';
    }

    private CreateMaterialResult(Material material) {
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

        public CreateMaterialResult build() {
            return new CreateMaterialResult(material);
        }
    }
}
