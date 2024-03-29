package com.nashss.se.musicplaylistservice.lambda.organizationLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.CreateOrganizationRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.CreateOrganizationResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class CreateOrganizationLambda
        extends LambdaActivityRunner<CreateOrganizationRequest, CreateOrganizationResult>
        implements RequestHandler<LambdaRequest<CreateOrganizationRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateOrganizationRequest> input, Context context) {
        return super.runActivity(
                () ->  {
                    return input.fromBody(CreateOrganizationRequest.class);
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateOrganizationActivity().handleRequest(request)
        );
    }
}
