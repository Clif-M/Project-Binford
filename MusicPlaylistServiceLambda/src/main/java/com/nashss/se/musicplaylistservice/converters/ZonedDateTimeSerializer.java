package com.nashss.se.musicplaylistservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nashss.se.musicplaylistservice.exceptions.MaterialSerializationException;

import java.time.ZonedDateTime;

public class ZonedDateTimeSerializer implements DynamoDBTypeConverter <String, ZonedDateTime>{
    private ObjectMapper objectMapper = new ObjectMapper();
    JavaTimeModule javaTimeModule = new JavaTimeModule();

    @Override
    public String convert(ZonedDateTime zonedDateTime) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.writeValueAsString(zonedDateTime);
        } catch (JsonProcessingException e) {
            throw new MaterialSerializationException("Error serializing zoned date time", e);
        }
    }

    @Override
    public ZonedDateTime unconvert(String taskString) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.readValue(taskString, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new MaterialSerializationException("Error deserializing zoned date time", e);
        }
    }
}