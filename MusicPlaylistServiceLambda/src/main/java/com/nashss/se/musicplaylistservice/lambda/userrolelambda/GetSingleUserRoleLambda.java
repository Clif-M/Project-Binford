package com.nashss.se.musicplaylistservice.lambda.userrolelambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetSingleUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetSingleUserRoleResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetSingleUserRoleLambda
        extends LambdaActivityRunner<GetSingleUserRoleRequest, GetSingleUserRoleResult>
        implements RequestHandler<LambdaRequest<GetSingleUserRoleRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(LambdaRequest<GetSingleUserRoleRequest> input, Context context) {
            return super.runActivity(
                    () -> input.fromPath(path ->
                            GetSingleUserRoleRequest.builder()
                                    .withEmail(path.get("userEmail"))
                                    .withOrgId(path.get("orgId"))
                                    .build()),
                    (request, serviceComponent) ->
                            serviceComponent.provideGetSingleUserRoleActivity().handleRequest(request)
            );
        }
}
