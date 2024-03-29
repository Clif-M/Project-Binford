package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.DeleteMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.DeleteMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;

import javax.inject.Inject;

/**
 * Implementation of DeleteMaterialActivity for Project Binford's DeleteMaterialActivity API
 *
 * This API allows a consumer to Delete a Single Material object with orgId and materialId.
 */
public class DeleteMaterialActivity {
    private final MaterialDao materialDao;

    /**
     * Instantiates a new DeleteMaterialActivity object
     *
     * @param materialDao MaterialDao to interact with the material table
     */

    @Inject
    public DeleteMaterialActivity(MaterialDao materialDao)  {
        this.materialDao=materialDao;
    }

    /**
     * This method handles the request by accepting a DeleteMaterialRequest object
     * and deletes the Material object in the DB
     * @return {@link com.nashss.se.musicplaylistservice.dynamodb.models.Material}
     */
    public DeleteMaterialResult handleRequest(final DeleteMaterialRequest deleteMaterialRequest) {
        if (deleteMaterialRequest.getMaterialId() == null || deleteMaterialRequest.getOrgId() == null) {
            throw new InvalidAttributeValueException("Error deleting Material resource: required value cannot be null");
        }
        String orgId = deleteMaterialRequest.getOrgId();
        String materialId = deleteMaterialRequest.getMaterialId();
        Material material = materialDao.loadSingleMaterial(orgId,materialId);
        materialDao.deleteMaterial(material);
        return DeleteMaterialResult.builder()
                .withMaterial(material)
                .build();
    }
}
