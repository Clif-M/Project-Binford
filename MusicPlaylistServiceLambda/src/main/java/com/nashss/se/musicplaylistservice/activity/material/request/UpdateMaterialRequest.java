package com.nashss.se.musicplaylistservice.activity.material.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateMaterialRequest.Builder.class)
public class UpdateMaterialRequest {
    private final String name;
    private final String orgId;
    private final String materialId;
    private final Double cost;
    private final Boolean isExpendable;
    private final Integer inventoryCount;

    public UpdateMaterialRequest(String name, String orgId, String materialId, Double cost, Boolean isExpendable, Integer count) {
        this.name = name;
        this.orgId = orgId;
        this.materialId = materialId;
        this.cost = cost;
        this.isExpendable = isExpendable;
        inventoryCount = count;
    }

    public String getName() {
        return name;
    }

    public String getOrgId() {
        return orgId;
    }

    public String getMaterialId() {
        return materialId;
    }

    public Double getCost() {
        return cost;
    }

    public Boolean getIsExpendable() {
        return isExpendable;
    }

    public Integer getInventoryCount() {
        return inventoryCount;
    }

    @Override
    public String toString() {
        return "UpdatePlaylistRequest{" +
                "name='" + name + '\'' +
                ", orgID='" + orgId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", cost='" + cost + '\'' +
                ", isExpendable='" + isExpendable + '\'' +
                ", inventoryCount='" + inventoryCount +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String name;
        private String orgId;
        private String materialId;
        private Double cost;
        private Boolean isExpendable;
        private Integer inventoryCount;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withOrgId(String orgId) {
            this.orgId = orgId;
            return this;
        }

        public Builder withMaterialId(String materialId) {
            this.materialId = materialId;
            return this;
        }

        public Builder withCost(Double cost) {
            this.cost = cost;
            return this;
        }

        public Builder withIsExpendable(Boolean isExpendable) {
            this.isExpendable = isExpendable;
            return this;
        }

        public Builder withInventoryCount(Integer inventoryCount) {
            this.inventoryCount = inventoryCount;
            return this;
        }

        public UpdateMaterialRequest build() {
            return new UpdateMaterialRequest(name, orgId, materialId, cost, isExpendable, inventoryCount);
        }
    }
}
