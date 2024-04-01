package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.exceptions.MaterialSerializationException;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListConverterTest {
    private TaskListConverter converter;
    private List<Task> objectList;
    private Task task1;
    private Task task2;
    private String serial;

    @BeforeEach
    public void setup() {
        converter = new TaskListConverter();
        task1 = new Task();
        task1.setOrgId("1");
        task1.setTaskId("1");
        task1.setAssignee("Elvis");
        task2 = new Task();
        task2.setOrgId("2");
        task2.setTaskId("2");
        task2.setAssignee("Madonna");
        objectList = new ArrayList<>();
        objectList.add(task1);
        objectList.add(task2);
        serial = "[{\"orgId\":\"1\",\"taskId\":\"1\",\"assignee\":\"Elvis\",\"completed\":null,\"hoursToComplete\":null,\"materialsList\":null,\"name\":null,\"startTime\":null,\"stopTime\":null},{\"orgId\":\"2\",\"taskId\":\"2\",\"assignee\":\"Madonna\",\"completed\":null,\"hoursToComplete\":null,\"materialsList\":null,\"name\":null,\"startTime\":null,\"stopTime\":null}]";
    }

    @Test
    public void convert_listOfTasks_resultIsAString() {
        //GIVEN
        //WHEN
        String result = converter.convert(objectList);

        //THEN
        assertEquals(String.class, result.getClass(), "Expected result to be a string");
        assertTrue(result.contains(task1.getAssignee()) && result.contains(task2.getAssignee()), "Expected result to contain serialized elements");
    }

    @Test
    public void unconvert_serialTaskString_resultIsATask() {
        //GIVEN
        //WHEN
        List<Task> result = converter.unconvert(serial);

        //THEN
        assertEquals(Task.class, result.get(0).getClass(), "Expected result to be a task");
        assertTrue(result.get(0).getAssignee().equals("Elvis") || result.get(0).getAssignee().equals("Madonna"), "Expected result to contain elements of serialized string");
    }
}
