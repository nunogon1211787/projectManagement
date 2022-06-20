package switch2021.project.interfaceAdapters.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import switch2021.project.applicationServices.service.ProjectService;
import switch2021.project.dtoModel.dto.EditProjectInfoDTO;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.Budget;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    ProjectController ctrl;

    @MockBean
    ProjectService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void getAllProjectSuccess() {
        OutputProjectDTO test = mock(OutputProjectDTO.class);
        OutputProjectDTO test2 = mock(OutputProjectDTO.class);
        OutputProjectDTO test3 = mock(OutputProjectDTO.class);
        when(service.showAllProjects()).thenReturn(CollectionModel.of
                (List.of(new OutputProjectDTO[]{test, test2, test3})));

        ResponseEntity<?> response = ctrl.showAllProjects();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getAllProjectCatchException() {
        ResponseEntity<?> response = ctrl.showAllProjects();

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void getProjectRequestedSuccess() {
        OutputProjectDTO test = mock(OutputProjectDTO.class);
        String x = "1";
        when(test.getCode()).thenReturn(x);
        when(service.showProject(x)).thenReturn(test);

        ResponseEntity<?> response = ctrl.showProjectRequested(x);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getProjectRequestedCatchException() {
        when(service.showProject(anyString())).thenThrow(Exception.class);

        ResponseEntity<?> response = ctrl.showProjectRequested("1");

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @SneakyThrows
    @Test
    void testCreateProject() {
        ProjectDTO test = mock(ProjectDTO.class);
        OutputProjectDTO outTest= mock(OutputProjectDTO.class);
        when(service.createAndSaveProject(test)).thenReturn(outTest);

        ResponseEntity<?> response = ctrl.createProject(test);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @SneakyThrows
    @Test
    void testCreateProjectException() {
        ProjectDTO test = mock(ProjectDTO.class);
        when(service.createAndSaveProject(test)).thenThrow(Exception.class);

        ResponseEntity<?> response = ctrl.createProject(test);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testUpdateProject() {
        EditProjectInfoDTO test = mock(EditProjectInfoDTO.class);
        OutputProjectDTO outTest= mock(OutputProjectDTO.class);
        when(service.updateProjectPartially("1", test)).thenReturn(outTest);

        ResponseEntity<?> response = ctrl.updateProjectPartially("1", test);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testUpdateProjectException() {
        EditProjectInfoDTO test = mock(EditProjectInfoDTO.class);
        Project project = mock(Project.class);
        Budget budget = mock(Budget.class);
        doThrow(IllegalArgumentException.class).when(project).setBudget(budget);
        when(service.updateProjectPartially(any(), any())).thenThrow(IllegalArgumentException.class);

        ResponseEntity<?> response = ctrl.updateProjectPartially("1", test);

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testDeleteProject() {
        ProjectDTO projDto = mock(ProjectDTO.class);
        projDto.code = "1";
        ctrl.createProject(projDto);

        ResponseEntity<?> response = ctrl.deleteProjectRequest("1");

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void testDeleteProjectException() {
        ProjectID projId = new ProjectID("1");
        doThrow(Exception.class).when(service).deleteProjectRequest(projId);

        ResponseEntity<?> response = ctrl.deleteProjectRequest("1");

        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

}
