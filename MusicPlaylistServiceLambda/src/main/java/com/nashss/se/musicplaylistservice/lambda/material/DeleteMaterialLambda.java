package com.nashss.se.musicplaylistservice.lambda.material;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.material.request.DeleteMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.DeleteMaterialResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class DeleteMaterialLambda
        extends LambdaActivityRunner<DeleteMaterialRequest, DeleteMaterialResult>
        implements RequestHandler<LambdaRequest<DeleteMaterialRequest>, LambdaResponse>
{

    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteMaterialRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        DeleteMaterialRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .withMaterialId(path.get("materialId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideDeleteMaterialActivity().handleRequest(request)
        );
    }
}
