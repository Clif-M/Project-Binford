package com.nashss.se.musicplaylistservice.lambda.taskLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTaskRequest;
import com.nashss.se.musicplaylistservice.activity.requests.taskRequests.GetTasksForAssigneeRequest;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTaskResult;
import com.nashss.se.musicplaylistservice.activity.results.taskResults.GetTasksForAssigneeResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetTasksForAssigneeLambda
    extends LambdaActivityRunner<GetTasksForAssigneeRequest, GetTasksForAssigneeResult>
    implements RequestHandler<LambdaRequest<GetTasksForAssigneeRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetTasksForAssigneeRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetTasksForAssigneeRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .withAssignee(path.get("assignee"))
                                .build()),
                        (request, serviceComponent) ->
                                serviceComponent.provideGetTasksForAssigneeActivity().handleRequest(request)
        );
    }
}
