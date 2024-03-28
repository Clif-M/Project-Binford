package com.nashss.se.musicplaylistservice.lambda.organizationLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.UpdateOrganizationRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.UpdateOrganizationResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class UpdateOrganizationLambda
        extends LambdaActivityRunner<UpdateOrganizationRequest, UpdateOrganizationResult>
        implements RequestHandler<LambdaRequest<UpdateOrganizationRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateOrganizationRequest> input, Context context) {
        return super.runActivity(
                () ->
                    input.fromPath(path ->
                            UpdateOrganizationRequest.builder()
                                    .withOrgId(path.get("orgId"))
                                    .withDisplayName(input.fromBody(UpdateOrganizationRequest.class).getDisplayName())
                                    .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateOrganizationActivity().handleRequest(request)
        );
    }
}
