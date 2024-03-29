package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.UpdateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.UpdateMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;

import javax.inject.Inject;

/**
 * Implementation of UpdateMaterialActivity for Project Binford's UpdateMaterialActivity API
 *
 * This API allows a consumer to update a Single Material object with orgId and materialId.
 */

public class UpdateMaterialActivity {
    private final MaterialDao materialDao;

    /**
     * Instantiates a new UpdateMaterialActivity object
     *
     * @param materialDao MaterialDao to interact with the material table
     */
    @Inject
    public UpdateMaterialActivity(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    /**
     * This method handles the request by accepting an UpdateMaterialRequest object
     * and saves the updated object to the DB
     * @return {@link com.nashss.se.musicplaylistservice.dynamodb.models.Material}
     */
    public UpdateMaterialResult handleRequest(UpdateMaterialRequest updateMaterialRequest) {
        if (updateMaterialRequest.getMaterialId() == null || updateMaterialRequest.getOrgId() == null) {
            throw new InvalidAttributeValueException("Error updating Material resource: required value cannot be null");
        }
        Material material = materialDao.loadSingleMaterial(updateMaterialRequest.getOrgId(), updateMaterialRequest.getMaterialId());
        material.setName(updateMaterialRequest.getName());
        material.setCost(updateMaterialRequest.getCost());
        material.setInventoryCount(updateMaterialRequest.getInventoryCount());
        material.setIsExpendable(updateMaterialRequest.getIsExpendable());

        materialDao.writeMaterial(material);

        return UpdateMaterialResult.builder()
                .withMaterial(material)
                .build();
    }
}
