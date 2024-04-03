package com.nashss.se.musicplaylistservice.activity.results.userroleresults;

import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;

public class GetSingleUserRoleResult {
    private final UserRole userRole;

    private GetSingleUserRoleResult(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserRole userRole;

        public Builder withSingleUserRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public GetSingleUserRoleResult build() {
            return new GetSingleUserRoleResult(userRole);
        }
    }
}
