package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.DeleteMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.request.GetMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.DeleteMaterialResult;
import com.nashss.se.musicplaylistservice.activity.material.result.GetMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

import javax.inject.Inject;

public class DeleteMaterialActivity {
    private final MaterialDao materialDao;

    @Inject
    public DeleteMaterialActivity(MaterialDao materialDao)  {
        this.materialDao=materialDao;
    }

    public DeleteMaterialResult handleRequest(final DeleteMaterialRequest deleteMaterialRequest) {
        String orgId = deleteMaterialRequest.getOrgId();
        String materialId = deleteMaterialRequest.getMaterialId();
        Material material = materialDao.loadSingleMaterial(orgId,materialId);
        materialDao.deleteMaterial(material);
        return DeleteMaterialResult.builder()
                .withMaterial(material)
                .build();
    }
}
