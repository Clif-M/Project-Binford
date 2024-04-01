package com.nashss.se.musicplaylistservice.lambda.userrolelambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUserRolesRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUserRolesResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetUserRolesLambda extends LambdaActivityRunner<GetUserRolesRequest, GetUserRolesResult>
    implements RequestHandler<LambdaRequest<GetUserRolesRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetUserRolesRequest> input, Context context) {
            return super.runActivity(
                    () -> input.fromPath(path ->
                            GetUserRolesRequest.builder()
                                    .withUserEmail(path.get("userEmail"))
                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideGetUserRolesActivity().handleRequest(request)
            );
        }
}
