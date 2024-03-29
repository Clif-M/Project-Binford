package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.UpdateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.UpdateMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateMaterialActivityTest {

    @Mock
    private MaterialDao materialDao;

    private UpdateMaterialActivity activity;
    private Material controlMaterial = new Material();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new UpdateMaterialActivity(materialDao);
        controlMaterial = new Material();
        controlMaterial.setOrgId(someString);
        controlMaterial.setMaterialId(someString);
        controlMaterial.setName(someString);
    }
    @Test
    public void handleRequest_validInput_ReturnsUpdatedMaterialResult() {
        //GIVEN
        UpdateMaterialRequest request = UpdateMaterialRequest.builder()
                .withMaterialId(someString)
                .withOrgId(someString)
                .withName(someString)
                .build();
        when(materialDao.loadSingleMaterial(any(String.class), any(String.class))).thenReturn(controlMaterial);

        //WHEN
        UpdateMaterialResult result = activity.handleRequest(request);
        //THEN
        assertEquals(request.getMaterialId(), result.getMaterial().getMaterialId());
        assertEquals(request.getOrgId(), result.getMaterial().getOrgId());
        assertEquals(request.getName(), result.getMaterial().getName());
        verify(materialDao, times(1)).writeMaterial(any(Material.class));
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        UpdateMaterialRequest request = UpdateMaterialRequest.builder()
                .withMaterialId(someString)
                .withOrgId(null)
                .withName(someString)
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
        UpdateMaterialRequest request = UpdateMaterialRequest.builder()
                .withMaterialId(null)
                .withOrgId(someString)
                .withName(someString)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }
}