package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

//TODO Serialize object
@DynamoDBTable(tableName = "ProjectBinford_MaterialTable")
public class Material {
    private String name;
    private String orgId;
    private String materialId;
    private Double cost;
    private Boolean isExpendable;
    private Integer inventoryCount;

    @DynamoDBAttribute(attributeName = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBHashKey(attributeName = "orgId")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @DynamoDBRangeKey(attributeName = "materialId")
    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    @DynamoDBAttribute(attributeName = "cost")
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @DynamoDBAttribute(attributeName = "isExpendable")
    @DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.B)
    public Boolean getExpendable() {
        return isExpendable;
    }

    public void setExpendable(Boolean expendable) {
        isExpendable = expendable;
    }

    @DynamoDBAttribute(attributeName = "inventoryCount")
    public Integer getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(Integer inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Material)) return false;
        Material material = (Material) o;
        return Objects.equals(orgId, material.orgId) && Objects.equals(materialId, material.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orgId, materialId);
    }
}
