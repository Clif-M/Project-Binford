package com.nashss.se.musicplaylistservice.activity.results.userroleresults;

import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;

public class CreateUserRoleResult {
    private final UserRole userRole;

    private CreateUserRoleResult(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getuserRole() {
        return userRole;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UserRole userRole;

        public Builder withUserRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        public CreateUserRoleResult build() {
            return new CreateUserRoleResult(userRole);
        }
    }
}
