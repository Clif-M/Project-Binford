package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.CreateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.CreateMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.mockito.Spy;

import java.util.UUID;

import javax.inject.Inject;

/**
 * Implementation of CreateMaterialActivity for Project Binford's CreateMaterialActivity API
 *
 * This API allows a consumer to create a material object with orgId and generates a UUID materialId
 */
public class CreateMaterialActivity {
    private MaterialDao materialDao;

    /**
     * Instantiates a new CreateMaterialActivity object
     *
     * @param materialDao MaterialDao to interact with the material table
     */

    @Inject
    public CreateMaterialActivity(MaterialDao materialDao) {
        this.materialDao=materialDao;
    }

    /**
     * This method handles the request by accepting a CreateMaterialRequest object and creating a new Material object to write to the DB
     * @return single {@link com.nashss.se.musicplaylistservice.dynamodb.models.Material} that was just written
     */
   public CreateMaterialResult handleRequest(CreateMaterialRequest createMaterialRequest) {
       if (createMaterialRequest.getOrgId() == null) {
           throw new InvalidAttributeValueException("Error retrieving Material resource: required value cannot be null");
       }
       Material material = new Material();
       material.setMaterialId(UUID.randomUUID().toString());
       material.setOrgId(createMaterialRequest.getOrgId());
       material.setName(createMaterialRequest.getName());
       material.setCost(createMaterialRequest.getCost());
       material.setInventoryCount(createMaterialRequest.getInventoryCount());
       material.setIsExpendable(createMaterialRequest.getIsExpendable());

       while (materialDao.checkIfExist(material)) {
           material.setMaterialId(UUID.randomUUID().toString());
       }

       materialDao.writeMaterial(material);

       return CreateMaterialResult.builder()
               .withMaterial(material)
               .build();
   }
}
