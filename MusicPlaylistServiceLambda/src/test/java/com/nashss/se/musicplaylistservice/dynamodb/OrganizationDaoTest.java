package com.nashss.se.musicplaylistservice.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.nashss.se.musicplaylistservice.dynamodb.models.Organization;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import com.nashss.se.musicplaylistservice.exceptions.OrganizationNotFoundException;
import com.nashss.se.musicplaylistservice.exceptions.TaskNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class OrganizationDaoTest {
    @Mock
    private DynamoDBMapper mapper;
    @Mock
    private PaginatedScanList<Task> paginatedScanList;
    private OrganizationDao organizationDao;
    private Organization testOrg;


    @BeforeEach
    public void setup() {
        initMocks(this);
        organizationDao = new OrganizationDao(mapper);
        testOrg = new Organization();
        testOrg.setOrgId("TestOrg");
        testOrg.setDisplayName("TestDisplayName");
    }

    @Test
    public void getSingleOrganization_MatchingOrganization_returnsOrganization() {
        //GIVEN
        doReturn(testOrg).when(mapper).load(Organization.class, testOrg.getOrgId());

        //WHEN
        Organization result = organizationDao.getSingleOrganization(testOrg.getOrgId());

        //THEN
        assertEquals(testOrg.getDisplayName(), result.getDisplayName(), "Expected method to pass back object with matching values");
        assertEquals(testOrg.getOrgId(), result.getOrgId(), "Expected method to pass back object with matching values");
    }

    @Test
    public void getSingleOrganization_noMatchingOrganization_ThrowsOrganizationNotFoundException() {
        //GIVEN
        doReturn(null).when(mapper).load(Organization.class, testOrg.getOrgId());

        //WHEN
        //THEN
        assertThrows(OrganizationNotFoundException.class, () -> organizationDao.getSingleOrganization(testOrg.getOrgId()), "Expected method to throw error");
    }

    @Test
    public void getAllOrgs_noInput_returnsList() {
        //GIVEN
        doReturn(paginatedScanList).when(mapper).scan(eq(Organization.class),any(DynamoDBScanExpression.class));
        //WHEN
        List<Organization> result = organizationDao.getAllOrgs();

        //THEN
        assertEquals(paginatedScanList.getClass(), result.getClass(), "Expected method to pass back a list");

    }

    @Test
    public void writeOrganization_anyOrg_interactsWithSaveMethod() {
        //GIVEN
        //WHEN
        organizationDao.writeOrganzation(testOrg);

        //THEN
        verify(mapper).save(testOrg);
    }

    @Test
    public void deleteOrganization_anyOrg_interactsWithDeleteMethod() {
        //GIVEN
        //WHEN
        organizationDao.deleteOrganization(testOrg);

        //THEN
        verify(mapper).delete(testOrg);
    }
}
