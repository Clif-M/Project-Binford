package com.nashss.se.musicplaylistservice.lambda.projectLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.DeleteProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.DeleteProjectResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class DeleteProjectLambda
        extends LambdaActivityRunner<DeleteProjectRequest, DeleteProjectResult>
        implements RequestHandler<LambdaRequest<DeleteProjectRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteProjectRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteProjectRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .withProjectId(path.get("projectId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteProjectActivity().handleRequest(request)
        );
    }
}
