package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.dynamodb.models.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserRoleDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private PaginatedQueryList<Task> paginatedQueryList;
    private UserRoleDao userRoleDao;
    private UserRole control;

    @BeforeEach
    public  void setUp() {
        initMocks(this);
        userRoleDao = new UserRoleDao(mapper);
        control = new UserRole();
        control.setUserEmail("test1234");
        control.setOrgId("1234test");
    }

    @Test
    public void loadUserRole_validIds_callsMapperLoad() {
        //GIVEN
        doReturn(control).when(mapper).load(UserRole.class, control.getUserEmail(), control.getOrgId());
        //WHEN
        userRoleDao.loadUserRole(control.getUserEmail(), control.getOrgId());
        //THEN

        verify(mapper).load(UserRole.class, control.getUserEmail(), control.getOrgId());
    }



    @Test
    public void loadUserRole_validIds_returnsRole() {
        //GIVEN
        doReturn(control).when(mapper).load(UserRole.class, control.getOrgId(), control.getOrgId());
        //WHEN
        UserRole result = userRoleDao.loadUserRole(control.getOrgId(), control.getOrgId());
        //THEN
        assertEquals(control.getUserEmail(), result.getUserEmail());
        assertEquals(control.getOrgId(), result.getOrgId());
    }
    @Test
    public void loadSingUserRole_invalidIds_throwsException() {
        //GIVEN
        doReturn(null).when(mapper).load(UserRole.class, control.getUserEmail(), control.getOrgId());
        //WHEN-THEN
        assertThrows(RuntimeException.class, () -> {
            userRoleDao.loadUserRole(control.getOrgId(), control.getUserEmail());
        });
    }

    @Test
    public void loadUserRolesForOrg_validOrd_ReturnsListofUserRoles() {
        //GIVEN
        ArgumentCaptor<DynamoDBQueryExpression<UserRole>> argumentCaptor = ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
        doReturn(paginatedQueryList).when(mapper).query(eq(UserRole.class), any(DynamoDBQueryExpression.class));
        //WHEN
        userRoleDao.loadUserRolesForEmail(control.getUserEmail());
        //THEN
        verify(mapper).query(eq(UserRole.class), argumentCaptor.capture());
    }

    @Test
    public void writeUserRole_callsMapperSave() {
        //GIVEN
        //WHEN
        userRoleDao.writeUserRole(control);

        //THEN
        verify(mapper).save(control);
    }

}
