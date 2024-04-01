package com.nashss.se.musicplaylistservice.lambda.projectLambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetPlaylistSongsRequest;
import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetAllProjectsRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPlaylistSongsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetAllProjectsResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetAllProjectsLambda
        extends LambdaActivityRunner<GetAllProjectsRequest, GetAllProjectsResult>
        implements RequestHandler<LambdaRequest<GetAllProjectsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetAllProjectsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath((path) ->
                        GetAllProjectsRequest.builder()
                        .withOrgId(path.get("orgId"))
                        .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetAllProjectsActivity().handleRequest(request)
        );
    }
}