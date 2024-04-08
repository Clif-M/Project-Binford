package com.nashss.se.musicplaylistservice.lambda.taskLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.CreateTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.UpdateTaskRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.CreateTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.UpdateTaskResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class CreateTaskLambda
    extends LambdaActivityRunner<CreateTaskRequest, CreateTaskResult>
    implements RequestHandler<LambdaRequest<CreateTaskRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateTaskRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    CreateTaskRequest unauthenticatedRequest = input.fromBody(CreateTaskRequest.class);
                    return CreateTaskRequest.builder()
                                    .withOrgId(unauthenticatedRequest.getOrgId())
                                    .withAssignee(unauthenticatedRequest.getAssignee())
                                    .withCompleted(unauthenticatedRequest.getCompleted())
                                    .withHoursToComplete(unauthenticatedRequest.getHoursToComplete())
                                    .withMaterialsList(unauthenticatedRequest.getMaterialsList())
                                    .withName(unauthenticatedRequest.getName())
                                    .withStartTime(unauthenticatedRequest.getStartTime())
                                    .withStopTime(unauthenticatedRequest.getStopTime())
                                    .withTaskNotes(unauthenticatedRequest.getTaskNotes())
                                    .build();
                },
                        (request, serviceComponent) ->
                                serviceComponent.provideCreateTaskActivity().handleRequest(request)
        );
    }
}
