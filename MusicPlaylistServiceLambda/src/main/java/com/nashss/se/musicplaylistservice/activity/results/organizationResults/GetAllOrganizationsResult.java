package com.nashss.se.musicplaylistservice.activity.results.organizationResults;

import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;

import java.util.List;

public class GetAllOrganizationsResult {
    private final List<Organization> organizationList;

    private GetAllOrganizationsResult(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<Organization> organizationList;

        public Builder withOrganizationList(List<Organization> organizationList) {
            this.organizationList = organizationList;
            return this;
        }

        public GetAllOrganizationsResult build() {
            return new GetAllOrganizationsResult(organizationList);
        }
    }
}
