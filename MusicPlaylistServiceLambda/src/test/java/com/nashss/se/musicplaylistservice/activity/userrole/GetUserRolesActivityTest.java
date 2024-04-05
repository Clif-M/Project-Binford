package com.nashss.se.musicplaylistservice.activity.userrole;

import com.nashss.se.musicplaylistservice.activity.requests.userrolerequests.GetUserRolesRequest;
import com.nashss.se.musicplaylistservice.activity.results.userroleresults.GetUserRolesResult;
import com.nashss.se.musicplaylistservice.activity.userroleactivities.CreateUserRoleActivity;
import com.nashss.se.musicplaylistservice.activity.userroleactivities.GetUserRolesActivity;
import com.nashss.se.musicplaylistservice.dynamodb.UserRoleDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import com.nashss.se.musicplaylistservice.exceptions.InvalidAttributeValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class GetUserRolesActivityTest {

    @Mock
    private UserRoleDao userRoleDao;

    private GetUserRolesActivity activity;
    private UserRole controlUserRole = new UserRole();

    private String someString = "TEST1234";

    @BeforeEach
    public void setUp() {
        initMocks(this);
        activity = new GetUserRolesActivity(userRoleDao);
        controlUserRole = new UserRole();
        controlUserRole.setOrgId(someString);
    }

    @Test
    void handleRequest_validRequest_returnsListMaterials() {
        //GIVEN
        UserRole controlUserRole2 = new UserRole();
        controlUserRole2.setUserEmail(someString+"f");
        GetUserRolesRequest request = GetUserRolesRequest.builder()
                .withUserEmail(someString)
                .build();
        List<UserRole> userList = new ArrayList<>();
        userList.add(controlUserRole);
        userList.add(controlUserRole2);
        when(userRoleDao.loadUserRolesForEmail(request.getUserEmail())).thenReturn(userList);
        //WHEN
        GetUserRolesResult result = activity.handleRequest(request);
        //THEN
        assertTrue(2 == result.getUserRoles().size());
        verify(userRoleDao, times(1)).loadUserRolesForEmail(someString);
    }

    @Test
    public void handleRequest_nullOrgId_throwsException() {
        //GIVEN
        GetUserRolesRequest request = GetUserRolesRequest.builder()
                .withUserEmail(null)
                .build();
        //WHEN
        //THEN
        assertThrows(InvalidAttributeValueException.class, () -> {
            activity.handleRequest(request);
        }, "Expected method to throw error when provided null value");
    }

}