package com.nashss.se.musicplaylistservice.activity.results.organizationResults;

import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;

public class GetOrganizationResult {
    private final Organization organization;

    private GetOrganizationResult(Organization organization) {
        this.organization = organization;
    }

    public Organization getOrganization() {
        return organization;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Organization organization;

        public Builder withOrganization(Organization organization) {
            this.organization = organization;
            return this;
        }

        public GetOrganizationResult build() {
            return new GetOrganizationResult(organization);
        }
    }
}
