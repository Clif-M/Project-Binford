package com.nashss.se.musicplaylistservice.lambda.projectLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.CreateProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.CreateProjectResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class CreateProjectLambda
        extends LambdaActivityRunner<CreateProjectRequest, CreateProjectResult>
        implements RequestHandler<LambdaRequest<CreateProjectRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateProjectRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    return input.fromBody(CreateProjectRequest.class);
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateProjectActivity().handleRequest(request)
        );
    }
}
