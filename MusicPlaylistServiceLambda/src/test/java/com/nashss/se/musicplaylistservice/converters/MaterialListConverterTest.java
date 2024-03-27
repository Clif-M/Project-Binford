package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.exceptions.MaterialSerializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MaterialListConverterTest {
    private MaterialListConverter converter;
    private List<Material> objectList;
    private Material material1;
    private Material material2;
    private String serial;

    @BeforeEach
    public void setup() {
        converter = new MaterialListConverter();
        material1 = new Material();
        material1.setOrgId("1");
        material1.setMaterialId("1");
        material1.setName("Elvis");
        material2 = new Material();
        material2.setOrgId("2");
        material2.setMaterialId("2");
        material2.setName("Madonna");
        objectList = new ArrayList<>();
        objectList.add(material1);
        objectList.add(material2);
        serial = "[{\"name\":\"Elvis\",\"orgId\":\"1\",\"materialId\":\"1\",\"cost\":null,\"inventoryCount\":null,\"expendable\":null},{\"name\":\"Madonna\",\"orgId\":\"2\",\"materialId\":\"2\",\"cost\":null,\"inventoryCount\":null,\"expendable\":null}]";
    }

    @Test
    public void convert_listOfTasks_resultIsAString() {
        //GIVEN
        //WHEN
        String result = converter.convert(objectList);

        //THEN
        assertEquals(String.class, result.getClass(), "Expected result to be a string");
        assertTrue(result.contains(material1.getName()) && result.contains(material2.getName()), "Expected result to contain serialized elements");
    }

    @Test
    public void unconvert_serialTaskString_resultIsATask() {
        //GIVEN
        //WHEN
        List<Material> result = converter.unconvert(serial);

        //THEN
        assertEquals(Material.class, result.get(0).getClass(), "Expected result to be a material");
        assertTrue(result.get(0).getName().equals("Elvis") || result.get(0).getName().equals("Madonna"), "Expected result to contain elements of serialized string");
    }

 /*   @Test
    public void convert_badInput_throwsMaterialSerializationException() {
        //GIVEN
        material2.setStartTime(ZonedDateTime.now());
        //WHEN
        //THEN
        assertThrows(MaterialSerializationException.class, () -> converter.convert(objectList));
    }*/
}
