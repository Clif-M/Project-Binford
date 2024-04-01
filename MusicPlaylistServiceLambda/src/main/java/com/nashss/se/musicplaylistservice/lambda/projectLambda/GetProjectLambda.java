package com.nashss.se.musicplaylistservice.lambda.projectLambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetProjectResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetProjectLambda
    extends LambdaActivityRunner<GetProjectRequest, GetProjectResult>
            implements RequestHandler<LambdaRequest<GetProjectRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetProjectRequest> input, Context context) {
            return super.runActivity(
                    () -> input.fromPath(path ->
                            GetProjectRequest.builder()
                                    .withOrgId(path.get("orgId"))
                                    .withProjectId(path.get("projectId"))
                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideGetProjectActivity().handleRequest(request)
            );
        }
    }
