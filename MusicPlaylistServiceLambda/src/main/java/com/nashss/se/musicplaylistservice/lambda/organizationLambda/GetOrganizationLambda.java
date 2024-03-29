package com.nashss.se.musicplaylistservice.lambda.organizationLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.GetOrganizationRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.GetOrganizationResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetOrganizationLambda
        extends LambdaActivityRunner<GetOrganizationRequest, GetOrganizationResult>
        implements RequestHandler<LambdaRequest<GetOrganizationRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetOrganizationRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetOrganizationRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetOrganizationActivity().handleRequest(request)
        );
    }
}
