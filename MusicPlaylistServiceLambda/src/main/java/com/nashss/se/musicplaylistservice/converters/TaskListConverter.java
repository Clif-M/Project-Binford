package com.nashss.se.musicplaylistservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.exceptions.MaterialSerializationException;

import java.util.List;

public class TaskListConverter implements DynamoDBTypeConverter <String, List<Task>>{
    private ObjectMapper objectMapper = new ObjectMapper();
    private JavaTimeModule javaTimeModule = new JavaTimeModule();


    @Override
    public String convert(List<Task> taskList) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.writeValueAsString(taskList);
        } catch (JsonProcessingException e) {
            throw new MaterialSerializationException("Error serializing tasks list", e);
        }
    }

    @Override
    public List<Task> unconvert(String taskString) {
        objectMapper.registerModule(javaTimeModule);
        try {
            return objectMapper.readValue(taskString, new TypeReference<>(){});
        } catch (JsonProcessingException e) {
            throw new MaterialSerializationException("Error deserializing tasks list", e);
        }
    }
}