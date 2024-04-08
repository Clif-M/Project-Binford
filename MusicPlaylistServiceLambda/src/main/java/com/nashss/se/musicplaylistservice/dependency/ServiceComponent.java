package com.nashss.se.musicplaylistservice.dependency;

import com.nashss.se.musicplaylistservice.activity.playlistactivities.AddSongToPlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.playlistactivities.CreatePlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.playlistactivities.GetPlaylistActivity;
import com.nashss.se.musicplaylistservice.activity.playlistactivities.GetPlaylistSongsActivity;
import com.nashss.se.musicplaylistservice.activity.playlistactivities.SearchPlaylistsActivity;
import com.nashss.se.musicplaylistservice.activity.playlistactivities.UpdatePlaylistActivity;


import com.nashss.se.musicplaylistservice.activity.userroleactivities.*;
import com.nashss.se.musicplaylistservice.activity.projectActivities.*;
import com.nashss.se.musicplaylistservice.activity.material.*;
import com.nashss.se.musicplaylistservice.activity.taskActivities.*;
import com.nashss.se.musicplaylistservice.activity.organizationActivities.*;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return CreatePlaylistActivity
     */
    CreatePlaylistActivity provideCreatePlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    GetPlaylistActivity provideGetPlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    SearchPlaylistsActivity provideSearchPlaylistsActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistSongsActivity
     */
    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();

    /**
     * Provides the relevant activity.
     * @return UpdatePlaylistActivity
     */
    UpdatePlaylistActivity provideUpdatePlaylistActivity();

    /**
     * Provides the relevant activity.
     * @return GetSingleUserRoleActivity
     */
    GetSingleUserRoleActivity provideGetSingleUserRoleActivity();

    /**
     * Provides the relevant activity.
     * @return GetUserRolesActivity
     */
    GetUserRolesActivity provideGetUserRolesActivity();

    /**
     * Provides the relevant activity.
     * @return CreateUserRoleActivity
     */
    CreateUserRoleActivity provideCreateUserRoleActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateUserRoleActivity
     */
    UpdateUserRoleActivity provideUpdateUserRoleActivity();

     /**
     * Provides the relevant activity.
     * @return GetSingleMaterialActivity
     */
    GetSingleMaterialActivity provideGetSingleMaterialActivity();

    /**
     * Provides the relevant activity.
     * @return GetOrgMaterialsActivity
     */
    GetOrgMaterialsActivity provideGetOrgMaterialsActivity();

    /**
     * Provides the relevant activity.
     * @return CreateMaterialsActivity
     */
    CreateMaterialActivity provideCreateMaterialActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateMaterialsActivity
     */
    UpdateMaterialActivity provideUpdateMaterialActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateMaterialsActivity
     */
    DeleteMaterialActivity provideDeleteMaterialActivity();
    /**
     * Provides the relevant activity
     * @return GetTaskActivity
     */
    GetTaskActivity provideGetTaskActivity();

    /**
     * Provides the relevant activity
     * @return GetTasksForOrgActivity
     */
    GetTasksForOrgActivity provideGetTasksForOrgActivity();

    /**
     * Provides the relevant activity
     * @return DeleteTaskActivity
     */
    DeleteTaskActivity provideDeleteTaskActivity();

    /**
     * Provides the relevant activity
     * @return GetTasksForAssigneeActivity
     */
    GetTasksForAssigneeActivity provideGetTasksForAssigneeActivity();

    /**
     * Provides the relevant activity
     * @return UpdateTaskActivity
     */
    UpdateTaskActivity provideUpdateTaskActivity();

    /**
     * Provides the relevant activity
     * @return CreateTaskActivity
     */
    CreateTaskActivity provideCreateTaskActivity();

    /**
     * Provides the relevant activity.
     * @return GetOrganizationActivity
     */
    GetOrganizationActivity provideGetOrganizationActivity();

    /**
     * Provides the relevant activity.
     * @return GetAllOrganizationsActivity
     */
    GetAllOrganizationsActivity provideGetAllOrganizationsActivity();

    /**
     * Provides the relevant activity.
     * @return CreateOrganizationActivity
     */
    CreateOrganizationActivity provideCreateOrganizationActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateOrganizationActivity
     */
    UpdateOrganizationActivity provideUpdateOrganizationActivity();

    /**
     * Provides the relevant activity.
     * @return DeleteOrganizationActivity
     */
    DeleteOrganizationActivity provideDeleteOrganizationActivity();

    /**
     * Provides the relevant activity.
     * @return GetProjectActivity
     */
    GetProjectActivity provideGetProjectActivity();

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
    //SearchProjectsActivity provideSearchProjectsActivityActivity();

    /**
     * Provides the relevant activity.
     * @return GetAllProjectsActivity
     */
    GetAllProjectsActivity provideGetAllProjectsActivity();

    /**
     * Provides the relevant activity
     * @return UpdateProjectActivity
     */
    UpdateProjectActivity provideUpdateProjectActivity();

    /**
     * Provides the relevant activity
     * @return CreateProjectActivity
     */
    CreateProjectActivity provideCreateProjectActivity();

    /**
     * Provides the relevant activity
     * @return DeleteProjectActivity
     */
    DeleteProjectActivity provideDeleteProjectActivity();

    /**
     * Provides the relevant activity
     * @return GetUsersForOrgActivity
     */
    GetUsersForOrgActivity provideGetUsersForOrgActivity();

    /**
     * Provides the relevant activity
     * @return GetDisplayRolesActivity
     */
    GetDisplayRolesActivity provideGetDisplayRolesActivity();
}
