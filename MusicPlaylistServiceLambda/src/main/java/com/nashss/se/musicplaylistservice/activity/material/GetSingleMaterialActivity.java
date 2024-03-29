package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.GetMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.GetMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;

import javax.inject.Inject;

/**
 * Implementation of GetSingleMaterialActivity for Project Binford's GetSingleMaterialActivity API
 *
 * This API allows a consumer to get a Single Material object with orgId and materialId.
 */

public class GetSingleMaterialActivity {

    private final MaterialDao materialDao;

    /**
     * Instantiates a new GetSingleMaterialActivity object
     *
     * @param materialDao MaterialDao to interact with the material table
     */

    @Inject
    public GetSingleMaterialActivity(MaterialDao materialDao)  {
        this.materialDao=materialDao;
    }


    /**
     * This method handles the request by accepting a GetMaterialsRequest object
     * and returns a Single Material objects from the DB
     * @return {@link com.nashss.se.musicplaylistservice.dynamodb.models.Material}
     */
    public GetMaterialResult handleRequest(final GetMaterialRequest getMaterialRequest) {
        if (getMaterialRequest.getMaterialId() == null || getMaterialRequest.getOrgId() == null) {
            throw new InvalidAttributeValueException("Error deleting Material resource: required value cannot be null");
        }
        String orgId = getMaterialRequest.getOrgId();
        String materialId = getMaterialRequest.getMaterialId();
        Material material = materialDao.loadSingleMaterial(orgId,materialId);
        return GetMaterialResult.builder()
                .withMaterial(material)
                .build();
    }

}
