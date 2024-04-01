package com.nashss.se.musicplaylistservice.activity.requests.userrolerequests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdateUserRoleRequest.Builder.class)
public class UpdateUserRoleRequest {
    private final String userEmail;
    private final String orgId;
    private final String jobRole;
    private final String displayName;
    private final String roleStatus;

    private UpdateUserRoleRequest(String userEmail, String orgId, String jobRole, String displayName, String roleStatus) {
        this.userEmail = userEmail;
        this.orgId = orgId;
        this.jobRole = jobRole;
        this.displayName = displayName;
        this.roleStatus = roleStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getOrgId() {
        return orgId;
    }


    public String getJobRole() {
        return jobRole;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getRoleStatus() {
        return roleStatus;
    }

    @Override
    public String toString() {
        return "CreatePlaylistRequest{" +
                "name='" + userEmail + '\'' +
                ", orgID='" + orgId + '\'' +
                ", cost='" + jobRole + '\'' +
                ", isExpendable='" + displayName + '\'' +
                ", inventoryCount='" + roleStatus +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder{
        private String userEmail;
        private String orgId;
        private String jobRole;
        private String displayName;
        private String roleStatus;

        public Builder withUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder withOrgId(String orgId) {
            this.orgId=orgId;
            return this;
        }


        public Builder withJobRole(String jobRole) {
            this.jobRole = jobRole;
            return this;
        }

        public Builder withDisplayName(String displayName) {
            this.displayName=displayName;
            return this;
        }

        public Builder withRoleStatus(String roleStatus) {
            this.roleStatus=roleStatus;
            return this;
        }

        public UpdateUserRoleRequest build() {
            return new UpdateUserRoleRequest(userEmail,orgId,jobRole,displayName,roleStatus);
        }
    }
}