package com.nashss.se.musicplaylistservice.activity.userrole;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetSingleUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetSingleUserRoleResult;
import com.nashss.se.musicplaylistservice.activity.userroleactivities.GetSingleUserRoleActivity;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class GetSingleUserRoleActivityTest {

    @Mock
    private UserRoleDao userRoleDao;

    private GetSingleUserRoleActivity activity;
    private UserRole controlUserRole = new UserRole();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new GetSingleUserRoleActivity(userRoleDao);
        controlUserRole = new UserRole();
        controlUserRole.setOrgId(someString);
    }

    @Test
    void handleRequest_validRequest_returnsResult() {
        //GIVEN
        GetSingleUserRoleRequest request = new GetSingleUserRoleRequest.Builder()
                .withEmail(someString)
                .withOrgId(someString)
                .build();
        when(userRoleDao.loadUserRole(someString,someString)).thenReturn(controlUserRole);
        //WHEN
        GetSingleUserRoleResult result = activity.handleRequest(request);
        //THEN
        assertEquals(someString, result.getUserRole().getOrgId());
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        GetSingleUserRoleRequest request = GetSingleUserRoleRequest.builder()
                .withEmail(null)
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
        GetSingleUserRoleRequest request = GetSingleUserRoleRequest.builder()
                .withEmail(null)
                .withOrgId(someString)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }
}