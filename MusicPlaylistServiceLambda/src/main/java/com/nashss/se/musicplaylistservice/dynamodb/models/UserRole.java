package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;

//TODO Serialize object
@DynamoDBTable(tableName = "ProjectBinford_UserRoleTable")
public class UserRole {
    private String userEmail;
    private String orgId;
    private String jobRole;
    private String displayName;


    @DynamoDBHashKey(attributeName = "userEmail")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @DynamoDBRangeKey(attributeName = "orgId")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @DynamoDBAttribute(attributeName = "jobRole")
    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    @DynamoDBAttribute(attributeName = "displayName")
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(getUserEmail(), userRole.getUserEmail()) && Objects.equals(getOrgId(), userRole.getOrgId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserEmail(), getOrgId());
    }
}