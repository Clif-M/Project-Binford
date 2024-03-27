package com.nashss.se.musicplaylistservice.activity.material.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreateMaterialRequest.Builder.class)
public class CreateMaterialRequest {

    private final String name;
    private final String orgId;
    private final String materialId;
    private final Double cost;
    private final Boolean isExpendable;
    private final Integer inventoryCount;

    private CreateMaterialRequest(String name, String orgId, String materialId, Double cost, Boolean isExpendable, Integer inventoryCount) {
        this.name = name;
        this.orgId = orgId;
        this.materialId = materialId;
        this.cost = cost;
        this.isExpendable = isExpendable;
        this.inventoryCount = inventoryCount;
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

    public Boolean getExpendable() {
        return isExpendable;
    }

    public Integer getInventoryCount() {
        return inventoryCount;
    }

    @Override
    public String toString() {
        return "CreatePlaylistRequest{" +
                "name='" + name + '\'' +
                ", orgID='" + orgId + '\'' +
                ", materialId='" + materialId + '\'' +
                ", cost='" + cost + '\'' +
                ", isExpendable='" + isExpendable + '\'' +
                ", inventoryCount='" + inventoryCount +
                '}';
    }
    public static Builder builder(){
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{
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
            this.orgId=orgId;
            return this;
        }

        public Builder withMaterialId(String materialId) {
            this.materialId=materialId;
            return this;
        }

        public Builder withCost(Double cost) {
            this.cost = cost;
            return this;
        }

        public Builder withExpendable(Boolean isExpendable) {
            this.isExpendable=isExpendable;
            return this;
        }

        public Builder withInventoryCount(Integer inventoryCount) {
            this.inventoryCount=inventoryCount;
            return this;
        }

        public CreateMaterialRequest build() {
            return new CreateMaterialRequest(name,orgId,materialId,cost,isExpendable,inventoryCount);
        }



    }
}
