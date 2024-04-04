package com.nashss.se.musicplaylistservice.activity.results.userroleresults;

import com.nashss.se.musicplaylistservice.dynamodb.models.UserDisplayRole;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;

import java.util.List;

public class GetDisplayRolesResult {
    private final List<UserDisplayRole> userDisplayRoles;

    private GetDisplayRolesResult(List<UserDisplayRole> userDisplayRoles) { this.userDisplayRoles = userDisplayRoles; }

    public List<UserDisplayRole> getUserDisplayRoles() {
        return userDisplayRoles;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private List<UserDisplayRole> userDisplayRoles;

        public Builder withUserDisplayRolesList(List<UserDisplayRole> userDisplayRoles) {
            this.userDisplayRoles = userDisplayRoles;
            return this;
        }

        public GetDisplayRolesResult build() { return new GetDisplayRolesResult(userDisplayRoles); }
    }
}

