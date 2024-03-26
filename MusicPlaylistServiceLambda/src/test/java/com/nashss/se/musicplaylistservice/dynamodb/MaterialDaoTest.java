package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MaterialDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private PaginatedQueryList<Task> paginatedQueryList;
    private MaterialDao materialDao;
    private Material control;

    @BeforeEach
    public  void setUp() {
        initMocks(this);
        materialDao = new MaterialDao(mapper);
        control = new Material();
        control.setOrgId("test1234");
        control.setMaterialId("1234test");
    }

    @Test
    public void loadSingMaterial_validIds_callsMapperLoad() {
        //GIVEN
        doReturn(control).when(mapper).load(Material.class, control.getOrgId(), control.getMaterialId());
        //WHEN
        materialDao.loadSingleMaterial(control.getOrgId(), control.getMaterialId());
        //THEN

        verify(mapper).load(Material.class, control.getOrgId(), control.getMaterialId());
    }



    @Test
    public void loadSingMaterial_validIds_returnsTask() {
        //GIVEN
        doReturn(control).when(mapper).load(Material.class, control.getOrgId(), control.getMaterialId());
        //WHEN
        Material result = materialDao.loadSingleMaterial(control.getOrgId(), control.getMaterialId());
        //THEN
        assertEquals(control.getOrgId(), result.getOrgId());
        assertEquals(control.getMaterialId(), result.getMaterialId());
    }
    @Test
    public void loadSingMaterial_invalidIds_throwsException() {
        //GIVEN
        doReturn(null).when(mapper).load(Material.class, control.getOrgId(), control.getMaterialId());
        //WHEN-THEN
        assertThrows(RuntimeException.class, () -> {
            materialDao.loadSingleMaterial(control.getOrgId(), control.getMaterialId());
        });
    }

    @Test
    public void loadMaterialsForOrg_validOrd_ReturnsListofMaterials() {
        //GIVEN
        ArgumentCaptor<DynamoDBQueryExpression<Material>> argumentCaptor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        doReturn(paginatedQueryList).when(mapper).query(eq(Material.class), any(DynamoDBQueryExpression.class));
        //WHEN
        materialDao.loadMaterialsForOrg(control.getOrgId());
        //THEN
        verify(mapper).query(eq(Material.class), argumentCaptor.capture());
    }

    @Test
    public void writeMaterial_callsMapperSave() {
        //GIVEN
        //WHEN
        materialDao.writeMaterial(control);

        //THEN
        verify(mapper).save(control);
    }

    @Test
    public void deleteMaterial_callsMapperDelete() {
        //GIVEN
        //WHEN
        materialDao.deleteMaterial(control);

        //THEN
        verify(mapper).delete(control);
    }

}
