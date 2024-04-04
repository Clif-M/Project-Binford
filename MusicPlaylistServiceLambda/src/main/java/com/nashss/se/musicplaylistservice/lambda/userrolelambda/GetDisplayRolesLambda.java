package com.nashss.se.musicplaylistservice.lambda.userrolelambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetDisplayRolesRequest;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUserRolesRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetDisplayRolesResult;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUserRolesResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetDisplayRolesLambda extends LambdaActivityRunner<GetDisplayRolesRequest, GetDisplayRolesResult>
    implements RequestHandler<LambdaRequest<GetDisplayRolesRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetDisplayRolesRequest> input, Context context) {
            return super.runActivity(
                    () -> input.fromPath(path ->
                            GetDisplayRolesRequest.builder()
                                    .withUserEmail(path.get("userEmail"))
                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideGetDisplayRolesActivity().handleRequest(request)
            );
        }
}
