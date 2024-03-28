package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.UpdateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.UpdateMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

import javax.inject.Inject;

public class UpdateMaterialActivity {
    private final MaterialDao materialDao;

    @Inject
    public UpdateMaterialActivity(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    public UpdateMaterialResult handleRequest(UpdateMaterialRequest createMaterialRequest) {
        Material material = materialDao.loadSingleMaterial(createMaterialRequest.getOrgId(), createMaterialRequest.getMaterialId());
        material.setMaterialId(createMaterialRequest.getMaterialId());
        material.setOrgId(createMaterialRequest.getOrgId());
        material.setName(createMaterialRequest.getName());
        material.setCost(createMaterialRequest.getCost());
        material.setInventoryCount(createMaterialRequest.getInventoryCount());
        material.setIsExpendable(createMaterialRequest.getIsExpendable());

        materialDao.writeMaterial(material);

        return UpdateMaterialResult.builder()
                .withMaterial(material)
                .build();
    }
}
