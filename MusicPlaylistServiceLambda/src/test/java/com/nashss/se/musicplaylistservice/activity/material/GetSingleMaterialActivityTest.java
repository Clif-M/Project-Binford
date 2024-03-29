package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.GetMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.GetMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class GetSingleMaterialActivityTest {

    @Mock
    private MaterialDao materialDao;

    private GetSingleMaterialActivity activity;
    private Material controlMaterial = new Material();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new GetSingleMaterialActivity(materialDao);
        controlMaterial = new Material();
        controlMaterial.setOrgId(someString);
    }

    @Test
    void handleRequest_validRequest_returnsResult() {
        //GIVEN
       GetMaterialRequest request = new GetMaterialRequest.Builder()
                .withMaterialId(someString)
                .withOrgId(someString)
                .build();
        when(materialDao.loadSingleMaterial(someString,someString)).thenReturn(controlMaterial);
        //WHEN
        GetMaterialResult result = activity.handleRequest(request);
        //THEN
        assertEquals(someString, result.getMaterial().getOrgId());
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        GetMaterialRequest request = GetMaterialRequest.builder()
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
        GetMaterialRequest request = GetMaterialRequest.builder()
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