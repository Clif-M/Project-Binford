package com.nashss.se.musicplaylistservice.lambda.projectLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetProjectRequest;
import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.UpdateProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetProjectResult;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.UpdateProjectResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class UpdateProjectLambda
        extends LambdaActivityRunner<UpdateProjectRequest, UpdateProjectResult>
        implements RequestHandler<LambdaRequest<UpdateProjectRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateProjectRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdateProjectRequest unauthenticatedRequest = input.fromBody(UpdateProjectRequest.class);
                    return input.fromPath(path ->
                            UpdateProjectRequest.builder()
                                    .withOrgId(path.get("orgId"))
                                    .withProjectId(path.get("projectId"))
                                    .withName(unauthenticatedRequest.getName())
                                    .withTaskList(unauthenticatedRequest.getTaskList())
                                    .withCompletionPercentage(unauthenticatedRequest.getCompletionPercentage())
                                    .withProjectStatus(unauthenticatedRequest.getProjectStatus())
                                    .withCreationDate(unauthenticatedRequest.getCreationDate())
                                    .withEndDate(unauthenticatedRequest.getEndDate())
                                    .withProjectDescription(unauthenticatedRequest.getProjectDescription())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateProjectActivity().handleRequest(request)
        );
    }
}
