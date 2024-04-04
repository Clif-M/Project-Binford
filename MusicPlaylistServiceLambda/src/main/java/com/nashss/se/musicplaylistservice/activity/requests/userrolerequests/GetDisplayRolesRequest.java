package com.nashss.se.musicplaylistservice.activity.requests.userrolerequests;

public class GetDisplayRolesRequest {
    private final String userEmail;

    private GetDisplayRolesRequest(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    //CHECKSTYLE:OFF:BUILDER
    public static  Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userEmail;

        public Builder withUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public GetDisplayRolesRequest build() {
            return new GetDisplayRolesRequest(userEmail);
        }
    }
}
