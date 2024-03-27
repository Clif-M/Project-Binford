package com.nashss.se.musicplaylistservice.lambda.material;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import com.nashss.se.musicplaylistservice.activity.material.request.GetOrgMaterialsRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.GetOrgMaterialsResult;
import com.nashss.se.musicplaylistservice.lambda.LambdaActivityRunner;
import com.nashss.se.musicplaylistservice.lambda.LambdaRequest;
import com.nashss.se.musicplaylistservice.lambda.LambdaResponse;

public class GetOrgMaterialsLambda
        extends LambdaActivityRunner<GetOrgMaterialsRequest, GetOrgMaterialsResult>
        implements RequestHandler<LambdaRequest<GetOrgMaterialsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetOrgMaterialsRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetOrgMaterialsRequest.builder()
                                .withOrgId(path.get("orgId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetOrgMaterialsActivity().handleRequest(request)
        );
    }
    }
