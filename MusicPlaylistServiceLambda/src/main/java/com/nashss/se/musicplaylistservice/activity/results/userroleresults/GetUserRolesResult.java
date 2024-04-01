package com.nashss.se.musicplaylistservice.activity.results.userroleresults;

import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;

import java.util.List;

    public class GetUserRolesResult {
        private final List<UserRole> userRoles;

        private GetUserRolesResult(List<UserRole> userRoles) { this.userRoles = userRoles; }

        public List<UserRole> getUserRoles() {
            return userRoles;
        }

        //CHECKSTYLE:OFF:Builder
        public static Builder builder() { return new Builder(); }

        public static class Builder {
            private List<UserRole> userRoles;

            public Builder withUserRoleList(List<UserRole> userRoles) {
                this.userRoles = userRoles;
                return this;
            }

            public GetUserRolesResult build() { return new GetUserRolesResult(userRoles); }
        }
    }

