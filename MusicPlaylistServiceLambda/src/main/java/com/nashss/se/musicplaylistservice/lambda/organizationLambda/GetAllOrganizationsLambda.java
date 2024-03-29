package com.nashss.se.musicplaylistservice.lambda.organizationLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.organizationRequests.GetAllOrganizationsRequest;
import com.nashss.se.musicplaylistservice.activity.results.organizationResults.GetAllOrganizationsResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetAllOrganizationsLambda
        extends LambdaActivityRunner<GetAllOrganizationsRequest, GetAllOrganizationsResult>
        implements RequestHandler<LambdaRequest<GetAllOrganizationsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllOrganizationsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetAllOrganizationsRequest.builder()
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllOrganizationsActivity().handleRequest(request)
        );
    }
}
