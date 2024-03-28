package com.nashss.se.musicplaylistservice.lambda.material;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.material.request.CreateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.CreateMaterialResult;
import com.nashss.se.musicplaylistservice.activity.requests.CreatePlaylistRequest;
import com.nashss.se.musicplaylistservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class CreateMaterialLambda
        extends LambdaActivityRunner<CreateMaterialRequest, CreateMaterialResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateMaterialRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateMaterialRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    return input.fromBody(CreateMaterialRequest.class);
                },
                (request, serviceComponent) ->
                        serviceComponent.provideCreateMaterialActivity().handleRequest(request)
        );
    }

}
