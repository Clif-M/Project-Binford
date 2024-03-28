package com.nashss.se.musicplaylistservice.lambda.organizationLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.DeleteOrganizationRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.DeleteOrganizationResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class DeleteOrganizationLambda
        extends LambdaActivityRunner<DeleteOrganizationRequest, DeleteOrganizationResult>
        implements RequestHandler<LambdaRequest<DeleteOrganizationRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteOrganizationRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteOrganizationRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteOrganizationActivity().handleRequest(request)
        );
    }
}
