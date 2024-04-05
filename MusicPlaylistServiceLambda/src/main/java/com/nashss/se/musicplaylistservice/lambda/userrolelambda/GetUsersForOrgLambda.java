package com.nashss.se.musicplaylistservice.lambda.userrolelambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUserRolesRequest;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUsersForOrgRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUserRolesResult;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUsersForOrgResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetUsersForOrgLambda extends LambdaActivityRunner<GetUsersForOrgRequest, GetUsersForOrgResult>
    implements RequestHandler<LambdaRequest<GetUsersForOrgRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetUsersForOrgRequest> input, Context context) {
            return super.runActivity(
                    () -> input.fromPath(path ->
                            GetUsersForOrgRequest.builder()
                                    .withOrgId(path.get("orgId"))
                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideGetUsersForOrgActivity().handleRequest(request)
            );
        }
}
