package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.GetOrgMaterialsRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.GetOrgMaterialsResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of GetOrgMaterialsActivity for Project Binford's GetOrgMaterialsActivity API
 *
 * This API allows a consumer to get a List of materials object with orgId.
 */
public class GetOrgMaterialsActivity {

    private final MaterialDao materialDao;

    /**
     * Instantiates a new GetOrgMaterialsActivity object
     *
     * @param materialDao MaterialDao to interact with the material table
     */

    @Inject
    public GetOrgMaterialsActivity(MaterialDao materialDao)  {
        this.materialDao=materialDao;
    }

    /**
     * This method handles the request by accepting a GetOrgMaterialsRequest object
     * and returns a List of Material objects from the DB
     * @return List {@link com.nashss.se.musicplaylistservice.dynamodb.models.Material}
     */

    public GetOrgMaterialsResult handleRequest(final GetOrgMaterialsRequest getOrgMaterialsRequest) {
        String orgId = getOrgMaterialsRequest.getOrgId();
        List<Material> materials = materialDao.loadMaterialsForOrg(orgId);
        return GetOrgMaterialsResult.builder()
                .withMaterials(materials)
                .build();
    }

}
