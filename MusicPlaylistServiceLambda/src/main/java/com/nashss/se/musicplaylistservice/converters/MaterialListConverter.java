package com.nashss.se.musicplaylistservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.musicplaylistservice.dynamodb.models.Material;
import com.nashss.se.musicplaylistservice.exceptions.MaterialSerializationException;

import java.util.List;

public class MaterialListConverter implements DynamoDBTypeConverter <String, List<Material>>{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(List<Material> materialList) {
        try {
            return objectMapper.writeValueAsString(materialList);
        } catch (JsonProcessingException e) {
            throw new MaterialSerializationException("Error serializing materials list", e);
        }
    }

    @Override
    public List<Material> unconvert(String materialString) {
        try {
            return objectMapper.readValue(materialString, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new MaterialSerializationException("Error deserializing materials list", e);
        }
    }
}