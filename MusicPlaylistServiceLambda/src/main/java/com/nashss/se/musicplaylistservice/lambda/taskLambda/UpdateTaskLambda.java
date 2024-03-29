package com.nashss.se.musicplaylistservice.lambda.taskLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.UpdateTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.UpdateTaskResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class UpdateTaskLambda
    extends LambdaActivityRunner<UpdateTaskRequest, UpdateTaskResult>
    implements RequestHandler<LambdaRequest<UpdateTaskRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateTaskRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateTaskRequest unauthenticatedRequest = input.fromBody(UpdateTaskRequest.class);
                    return input.fromPath(path ->
                            UpdateTaskRequest.builder()
                                    .withOrgId(path.get("orgId"))
                                    .withTaskId(path.get("taskId"))
                                    .withAssignee(unauthenticatedRequest.getAssignee())
                                    .withCompleted(unauthenticatedRequest.getCompleted())
                                    .withHoursToComplete(unauthenticatedRequest.getHoursToComplete())
                                    .withMaterialsList(unauthenticatedRequest.getMaterialsList())
                                    .withName(unauthenticatedRequest.getName())
                                    .withStartTime(unauthenticatedRequest.getStartTime())
                                    .withStopTime(unauthenticatedRequest.getStopTime())
                                    .build());
                },
                        (request, serviceComponent) ->
                                serviceComponent.provideUpdateTaskActivity().handleRequest(request)
        );
    }
}
