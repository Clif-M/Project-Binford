package com.nashss.se.musicplaylistservice.lambda.taskLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetTaskLambda
    extends LambdaActivityRunner<GetTaskRequest, GetTaskResult>
    implements RequestHandler<LambdaRequest<GetTaskRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTaskRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetTaskRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .withTaskId(path.get("taskId"))
                                .build()),
                        (request, serviceComponent) ->
                                serviceComponent.provideGetTaskActivity().handleRequest(request)
        );
    }
}
