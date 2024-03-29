package com.nashss.se.musicplaylistservice.lambda.taskLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.DeleteTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.DeleteTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class DeleteTaskLambda
    extends LambdaActivityRunner<DeleteTaskRequest, DeleteTaskResult>
    implements RequestHandler<LambdaRequest<DeleteTaskRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteTaskRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteTaskRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .withTaskId(path.get("taskId"))
                                .build()),
                        (request, serviceComponent) ->
                                serviceComponent.provideDeleteTaskActivity().handleRequest(request)
        );
    }
}
