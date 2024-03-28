package com.nashss.se.musicplaylistservice.activity.requests.organizationRequests;


public class GetAllOrganizationsRequest {

    private GetAllOrganizationsRequest() {

    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public GetAllOrganizationsRequest build() {
            return new GetAllOrganizationsRequest();
        }
    }
}
