package com.nashss.se.musicplaylistservice.lambda.material;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.material.request.GetMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.GetMaterialResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetSingleMaterialLambda
    extends LambdaActivityRunner<GetMaterialRequest, GetMaterialResult>
    implements RequestHandler<LambdaRequest<GetMaterialRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetMaterialRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetMaterialRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .withMaterialId(path.get("materialId"))
                                .build()),
                        (request, serviceComponent) ->
                                serviceComponent.provideGetSingleMaterialActivity().handleRequest(request)
                );
    }
}

