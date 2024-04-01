package com.nashss.se.musicplaylistservice.lambda.userrolelambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.CreateUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.CreateUserRoleResult;
import com.nashss.se.musicplaylistservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class CreateUserRoleLambda extends LambdaActivityRunner<CreateUserRoleRequest, CreateUserRoleResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateUserRoleRequest>, LambdaResponse> {

        @Override
        public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateUserRoleRequest> input, Context context) {
            return super.runActivity(
                    () -> {
                        return input.fromBody(CreateUserRoleRequest.class);
                    },
                    (request, serviceComponent) ->
                            serviceComponent.provideCreateUserRoleActivity().handleRequest(request)
            );
        }

    }
