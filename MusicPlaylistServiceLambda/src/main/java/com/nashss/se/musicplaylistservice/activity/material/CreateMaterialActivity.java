package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.CreateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.CreateMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

import javax.inject.Inject;

public class CreateMaterialActivity {
    private MaterialDao materialDao;

    @Inject
    public CreateMaterialActivity(MaterialDao materialDao) {
        this.materialDao=materialDao;
    }

   public CreateMaterialResult handleRequest(CreateMaterialRequest createMaterialRequest) {
       Material material = new Material();
       material.setMaterialId(createMaterialRequest.getMaterialId());
       material.setOrgId(createMaterialRequest.getOrgId());
       material.setName(createMaterialRequest.getName());
       material.setCost(createMaterialRequest.getCost());
       material.setInventoryCount(createMaterialRequest.getInventoryCount());
       material.setExpendable(createMaterialRequest.getExpendable());

       materialDao.writeMaterial(material);

       return CreateMaterialResult.builder()
               .withMaterial(material)
               .build();
   }
}
