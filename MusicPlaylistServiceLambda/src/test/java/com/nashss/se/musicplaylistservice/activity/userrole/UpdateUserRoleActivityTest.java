package com.nashss.se.musicplaylistservice.activity.userrole;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.UpdateUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.UpdateUserRoleResult;
import com.nashss.se.musicplaylistservice.activity.userroleactivities.UpdateUserRoleActivity;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UpdateUserRoleActivityTest {

    @Mock
    private UserRoleDao userRoleDao;

    private UpdateUserRoleActivity activity;
    private UserRole controlUserRole = new UserRole();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new UpdateUserRoleActivity(userRoleDao);
        controlUserRole = new UserRole();
        controlUserRole.setUserEmail(someString);
        controlUserRole.setOrgId(someString);
        controlUserRole.setJobRole(someString);
    }
    @Test
    public void handleRequest_validInput_ReturnsUpdatedMaterialResult() {
        //GIVEN
        UpdateUserRoleRequest request = UpdateUserRoleRequest.builder()
                .withUserEmail(someString)
                .withOrgId(someString)
                .withJobRole(someString)
                .build();
        when(userRoleDao.loadUserRole(any(String.class), any(String.class))).thenReturn(controlUserRole);

        //WHEN
        UpdateUserRoleResult result = activity.handleRequest(request);
        //THEN
        assertEquals(request.getUserEmail(), result.getuserRole().getUserEmail());
        assertEquals(request.getOrgId(), result.getuserRole().getOrgId());
        assertEquals(request.getJobRole(), result.getuserRole().getJobRole());
        verify(userRoleDao, times(1)).writeUserRole(any(UserRole.class));
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        UpdateUserRoleRequest request = UpdateUserRoleRequest.builder()
                .withUserEmail(someString)
                .withOrgId(null)
                .withJobRole(someString)
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
        UpdateUserRoleRequest request = UpdateUserRoleRequest.builder()
                .withUserEmail(null)
                .withOrgId(someString)
                .withJobRole(someString)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }
}