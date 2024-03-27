package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.GetOrgMaterialsRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.GetOrgMaterialsResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;

import javax.inject.Inject;
import java.util.List;

public class GetOrgMaterialsActivity {

    private final MaterialDao materialDao;

    @Inject
    public GetOrgMaterialsActivity(MaterialDao materialDao)  {
        this.materialDao=materialDao;
    }

    public GetOrgMaterialsResult handleRequest(final GetOrgMaterialsRequest getOrgMaterialsRequest) {
        String orgId = getOrgMaterialsRequest.getOrgId();
        List<Material> materials = materialDao.loadMaterialsForOrg(orgId);
        return GetOrgMaterialsResult.builder()
                .withMaterials(materials)
                .build();
    }

}
