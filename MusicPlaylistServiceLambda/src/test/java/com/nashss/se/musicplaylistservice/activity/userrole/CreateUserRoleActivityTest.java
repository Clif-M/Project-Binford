package com.nashss.se.musicplaylistservice.activity.userrole;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.CreateUserRoleRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.CreateUserRoleResult;
import com.nashss.se.musicplaylistservice.activity.userroleactivities.CreateUserRoleActivity;
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

class CreateUserRoleActivityTest {
    @Mock
    private UserRoleDao userRoleDao;

    private CreateUserRoleActivity activity;
    private UserRole controlUserRole = new UserRole();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new CreateUserRoleActivity(userRoleDao);
        controlUserRole = new UserRole();
        controlUserRole.setOrgId(someString);
    }

    @Test
    public void handleRequest_ValidRequest_GeneratesUserEmailandOrgID() {
        //GIVEN
        when(userRoleDao.checkIfExist(any(UserRole.class))).thenReturn(false);
        CreateUserRoleRequest request = CreateUserRoleRequest.builder().withUserEmail(someString).withOrgId(someString).build();
        //WHEN
        CreateUserRoleResult result = activity.handleRequest(request);
        //THEN
        assertEquals(request.getUserEmail(), result.getuserRole().getUserEmail());
        assertNotNull(result.getuserRole().getUserEmail());
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        CreateUserRoleRequest request = CreateUserRoleRequest.builder()
                .withOrgId(null)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }

}