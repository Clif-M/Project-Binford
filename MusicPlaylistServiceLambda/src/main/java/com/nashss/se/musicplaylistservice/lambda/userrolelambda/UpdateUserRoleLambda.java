package com.nashss.se.musicplaylistservice.lambda.userrolelambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.UpdateUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.UpdateUserRoleResult;
import com.nashss.se.musicplaylistservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class UpdateUserRoleLambda extends LambdaActivityRunner<UpdateUserRoleRequest, UpdateUserRoleResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateUserRoleRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateUserRoleRequest> input, Context context) {
            return super.runActivity(
                    () -> {
                        UpdateUserRoleRequest unAuthenticatedRequest = input.fromBody(UpdateUserRoleRequest.class);
                        return input.fromPath(path ->
                                UpdateUserRoleRequest.builder()
                                        .withOrgId(path.get("orgId"))
                                        .withUserEmail(path.get("userEmail"))
                                        .withJobRole(unAuthenticatedRequest.getJobRole())
                                        .withDisplayName(unAuthenticatedRequest.getDisplayName())
                                        .withRoleStatus(unAuthenticatedRequest.getRoleStatus())
                                        .build());
                    },
                    (request, serviceComponent) ->
                            serviceComponent.provideUpdateUserRoleActivity().handleRequest(request)
            );
        }
}
