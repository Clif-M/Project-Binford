package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.CreateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.request.DeleteMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.request.GetMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.DeleteMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DeleteMaterialActivityTest {

    @Mock
    private MaterialDao materialDao;

    private DeleteMaterialActivity activity;
    private Material controlMaterial = new Material();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new DeleteMaterialActivity(materialDao);
        controlMaterial = new Material();
        controlMaterial.setOrgId(someString);
    }
    @Test
    void handleRequest_validIds_callsDAODelete() {
        //GIVEN
        DeleteMaterialRequest request = new DeleteMaterialRequest.Builder()
                .withMaterialId(someString)
                .withOrgId(someString)
                .build();
        when(materialDao.loadSingleMaterial(someString,someString)).thenReturn(controlMaterial);
        //WHEN
        DeleteMaterialResult result = activity.handleRequest(request);
        //THEN
        verify(materialDao).deleteMaterial(any(Material.class));
        assertEquals(someString, result.getMaterial().getOrgId());
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        DeleteMaterialRequest request = DeleteMaterialRequest.builder()
                .withMaterialId(null)
                .withOrgId(someString)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }

    @Test
    public void handleRequest_nullMaterialId_throwsException() {
        //GIVEN
        DeleteMaterialRequest request = DeleteMaterialRequest.builder()
                .withMaterialId(null)
                .withOrgId(someString)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }
}