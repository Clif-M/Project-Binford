package com.nashss.se.musicplaylistservice.lambda.taskLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTasksForOrgRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTasksForOrgResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetTasksForOrgLambda
    extends LambdaActivityRunner<GetTasksForOrgRequest, GetTasksForOrgResult>
    implements RequestHandler<LambdaRequest<GetTasksForOrgRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTasksForOrgRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetTasksForOrgRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .build()),
                        (request, serviceComponent) ->
                                serviceComponent.provideGetTasksForOrgActivity().handleRequest(request)
        );
    }
}
