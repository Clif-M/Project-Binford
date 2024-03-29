package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.GetMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.request.GetOrgMaterialsRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.GetMaterialResult;
import com.nashss.se.musicplaylistservice.activity.material.result.GetOrgMaterialsResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.annotation.concurrent.Immutable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class GetOrgMaterialsActivityTest {

    @Mock
    private MaterialDao materialDao;

    private GetOrgMaterialsActivity activity;
    private Material controlMaterial = new Material();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new GetOrgMaterialsActivity(materialDao);
        controlMaterial = new Material();
        controlMaterial.setOrgId(someString);
    }

    @Test
    void handleRequest_validRequest_returnsListMaterials() {
        //GIVEN
        Material controlMaterial2 = new Material();
                controlMaterial2.setMaterialId(someString+"f");
        GetOrgMaterialsRequest request = GetOrgMaterialsRequest.builder()
                .withOrgId(someString)
                .build();
        List<Material> matsList = new ArrayList<>();
        matsList.add(controlMaterial);
        matsList.add(controlMaterial2);
        when(materialDao.loadMaterialsForOrg(request.getOrgId())).thenReturn(matsList);
        //WHEN
        GetOrgMaterialsResult result = activity.handleRequest(request);
        //THEN
        assertTrue(2 == result.getMaterials().size());
        verify(materialDao, times(1)).loadMaterialsForOrg(someString);
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        GetOrgMaterialsRequest request = GetOrgMaterialsRequest.builder()
                .withOrgId(null)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }

}