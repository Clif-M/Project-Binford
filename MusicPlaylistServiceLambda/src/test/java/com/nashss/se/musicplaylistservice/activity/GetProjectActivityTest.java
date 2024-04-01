import com.nashss.se.musicplaylistservice.activity.projectActivities.GetProjectActivity;
import com.nashss.se.musicplaylistservice.activity.requests.projectRequests.GetProjectRequest;
import com.nashss.se.musicplaylistservice.activity.results.projectResults.GetProjectResult;
import com.nashss.se.musicplaylistservice.dynamodb.ProjectDao;
import com.nashss.se.musicplaylistservice.dynamodb.TaskDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Project;
import com.nashss.se.musicplaylistservice.dynamodb.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetProjectActivityTest {
    @Mock
    private ProjectDao projectDao;

    private GetProjectActivity activity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        activity = new GetProjectActivity(projectDao);
    }

    @Test
    public void handleRequest_goodRequest_callsDaoLoadMethod() {
        // GIVEN
        String orgId = "orgId";
        String projectId = "projectId";
        Project project = new Project();
        project.setOrgId(orgId);
        project.setProjectId(projectId);
        GetProjectRequest request = GetProjectRequest.builder()
                .withOrgId(orgId)
                .withProjectId(projectId)
                .build();

        doReturn(project).when(projectDao).getSingleProject(orgId, projectId);

        // WHEN
        GetProjectResult result = activity.handleRequest(request);

        // THEN
        verify(projectDao).getSingleProject(orgId,projectId);
        assertEquals(orgId, result.getProject().getOrgId(), "Expected method to output result with values matching those provided in request");
        assertEquals(projectId, result.getProject().getProjectId(), "Expected method to output result with values matching those provided in request");
    }
}



