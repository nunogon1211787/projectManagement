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
import switch2021.project.dtoModel.dto.PartialProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.entities.aggregates.Project.Project;
import switch2021.project.entities.valueObjects.vos.Budget;
import switch2021.project.entities.valueObjects.vos.ProjectID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        //Arrange
        Map<String, CollectionModel<PartialProjectDTO>> allProjectsDto = new HashMap<>();
        PartialProjectDTO test = mock(PartialProjectDTO.class);
        PartialProjectDTO test2 = mock(PartialProjectDTO.class);
        PartialProjectDTO test3 = mock(PartialProjectDTO.class);
        allProjectsDto.put("one", CollectionModel.of(List.of(test)));
        allProjectsDto.put("two", CollectionModel.of(List.of(test2)));
        allProjectsDto.put("three", CollectionModel.of(List.of(test3)));
        when(service.getAllProjects()).thenReturn(allProjectsDto);
        //Act
        ResponseEntity<?> response = ctrl.getAllProjects();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void getAllProjectCatchException() {
        //Arrange
        //Act
        ResponseEntity<?> response = ctrl.getAllProjects();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getProjectRequestedSuccess() {
        //Arrange
        OutputProjectDTO test = mock(OutputProjectDTO.class);
        String x = "1";
        when(test.getCode()).thenReturn(x);
        when(service.showProject(x)).thenReturn(test);
        //Act
        ResponseEntity<?> response = ctrl.showProjectRequested(x);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getProjectRequestedCatchException() {
        //Arrange
        when(service.showProject(anyString())).thenThrow(Exception.class);
        //Act
        ResponseEntity<?> response = ctrl.showProjectRequested("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @SneakyThrows
    @Test
    void testCreateProject() {
        //Arrange
        ProjectDTO test = mock(ProjectDTO.class);
        OutputProjectDTO outTest = mock(OutputProjectDTO.class);
        when(service.createAndSaveProject(test)).thenReturn(outTest);
        //Act
        ResponseEntity<?> response = ctrl.createProject(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @SneakyThrows
    @Test
    void testCreateProjectException() {
        //Arrange
        ProjectDTO test = mock(ProjectDTO.class);
        when(service.createAndSaveProject(test)).thenThrow(Exception.class);
        //Act
        ResponseEntity<?> response = ctrl.createProject(test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testUpdateProject() {
        //Arrange
        EditProjectInfoDTO test = mock(EditProjectInfoDTO.class);
        OutputProjectDTO outTest = mock(OutputProjectDTO.class);
        when(service.updateProjectPartially("1", test)).thenReturn(outTest);
        //Act
        ResponseEntity<?> response = ctrl.updateProjectPartially("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void testUpdateProjectException() {
        //Arrange
        EditProjectInfoDTO test = mock(EditProjectInfoDTO.class);
        Project project = mock(Project.class);
        Budget budget = mock(Budget.class);
        doThrow(IllegalArgumentException.class).when(project).setBudget(budget);
        when(service.updateProjectPartially(any(), any())).thenThrow(IllegalArgumentException.class);
        //Act
        ResponseEntity<?> response = ctrl.updateProjectPartially("1", test);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }

    @SneakyThrows
    @Test
    void testDeleteProject() {
        //Arrange
        ProjectDTO projDto = mock(ProjectDTO.class);
        projDto.code = "1";
        ctrl.createProject(projDto);
        //Act
        ResponseEntity<?> response = ctrl.deleteProjectRequest("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void testDeleteProjectException() {
        //Arrange
        String projId = "1";
        doThrow(IllegalArgumentException.class).when(service).deleteProjectRequest(projId);
        //Act
        ResponseEntity<?> response = ctrl.deleteProjectRequest("1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

}
