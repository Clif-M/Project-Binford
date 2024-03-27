package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.GetMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.GetMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

import javax.inject.Inject;

public class GetSingleMaterialActivity {

    private final MaterialDao materialDao;

    @Inject
    public GetSingleMaterialActivity(MaterialDao materialDao)  {
        this.materialDao=materialDao;
    }

    public GetMaterialResult handleRequest(final GetMaterialRequest getMaterialRequest) {
        String orgId = getMaterialRequest.getOrgId();
        String materialId = getMaterialRequest.getMaterialId();
        Material material = materialDao.loadSingleMaterial(orgId,materialId);
        return GetMaterialResult.builder()
                .withMaterial(material)
                .build();
    }

}
