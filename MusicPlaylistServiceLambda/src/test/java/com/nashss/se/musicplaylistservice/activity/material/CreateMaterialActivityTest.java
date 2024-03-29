package com.nashss.se.musicplaylistservice.activity.material;

import com.nashss.se.musicplaylistservice.activity.material.request.CreateMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.request.GetMaterialRequest;
import com.nashss.se.musicplaylistservice.activity.material.result.CreateMaterialResult;
import com.nashss.se.musicplaylistservice.dynamodb.MaterialDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class CreateMaterialActivityTest {
    @Mock
    private MaterialDao materialDao;

    private CreateMaterialActivity activity;
    private Material controlMaterial = new Material();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new CreateMaterialActivity(materialDao);
        controlMaterial = new Material();
        controlMaterial.setOrgId(someString);
    }

    @Test
    public void handleRequest_ValidRequest_GeneratesMaterialId() {
        //GIVEN
        when(materialDao.checkIfExist(any(Material.class))).thenReturn(false);
        CreateMaterialRequest request = CreateMaterialRequest.builder().withOrgId(someString).build();
        //WHEN
        CreateMaterialResult result = activity.handleRequest(request);
        //THEN
        assertEquals(request.getOrgId(), result.getMaterial().getOrgId());
        assertNotNull(result.getMaterial().getMaterialId());
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        CreateMaterialRequest request = CreateMaterialRequest.builder()
                .withOrgId(null)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }

}