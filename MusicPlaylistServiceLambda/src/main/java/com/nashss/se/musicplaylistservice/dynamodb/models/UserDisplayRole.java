package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

public class UserDisplayRole {
    private String userEmail;
    private String orgId;
    private String jobRole;
    private String displayName;
    private String roleStatus;
    private String orgDisplayName;

    public UserDisplayRole (String userEmail, String orgId, String jobRole, String displayName, String roleStatus, String orgDisplayName) {
        this.userEmail = userEmail;
        this.orgId = orgId;
        this.jobRole = jobRole;
        this.displayName = displayName;
        this.roleStatus = roleStatus;
        this.orgDisplayName = orgDisplayName;
    }

    public String getOrgDisplayName() { return orgDisplayName; }

    public void setOrgDisplayName(String orgDisplayName) { this.orgDisplayName = orgDisplayName; }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!this.getClass().equals(o.getClass())) {
            return false;
        }

        UserDisplayRole other = (UserDisplayRole) o;
        return (this.userEmail.equals(other.userEmail) && this.orgId.equals(other.orgId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userEmail + this.orgId);
    }
}