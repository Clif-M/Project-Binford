package com.nashss.se.musicplaylistservice.lambda.material;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.material.request.UpdateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.UpdateMaterialResult;
import com.nashss.se.musicplaylistservice.lambda.AuthenticatedLambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class UpdateMaterialLambda
        extends LambdaActivityRunner<UpdateMaterialRequest, UpdateMaterialResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateMaterialRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateMaterialRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    return input.fromBody(UpdateMaterialRequest.class);
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdateMaterialActivity().handleRequest(request)
        );
    }
}
