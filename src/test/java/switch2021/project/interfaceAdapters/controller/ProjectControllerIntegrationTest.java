package switch2021.project.interfaceAdapters.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.applicationServices.service.ProjectService;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.PartialProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.dtoModel.dto.TypologyDTO;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)  /*this makes the tests on this class run by alphabetical order */
class ProjectControllerIntegrationTest {

    @Autowired
    ProjectController ctrl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProjectService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }



        @Test
        void ZshouldReturnNewProjectAndOk() throws Exception {
            ProjectDTO projectDTO = new ProjectDTO();
            TypologyDTO typologyDTO = new TypologyDTO();

            typologyDTO.description = "fixed cost";

            projectDTO.projectName = "name";
            projectDTO.description = "description";
            projectDTO.businessSector = "sector";
            projectDTO.startDate = "2028-12-12";
            projectDTO.sprintDuration = "22";
            projectDTO.numberOfSprints = "33";
            projectDTO.budget = "11";
            projectDTO.typology = "Fixed cost";
            projectDTO.customer = "customer";

//            MvcResult resulttypo = mockMvc
//                    .perform(MockMvcRequestBuilders.post("/typologies")
//                            .contentType("application/json")
//                            .content(objectMapper.writeValueAsString(typologyDTO))
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andExpect(status().isCreated())
//                    .andReturn();

            MvcResult result = mockMvc
                    .perform(MockMvcRequestBuilders.post("/projects")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(projectDTO))
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andReturn();

            String resultContent = result.getResponse().getContentAsString();
            assertNotNull(resultContent);
            assertTrue(resultContent.contains("\"projectName\":\"name\""));
            assertTrue(resultContent.contains("\"description\":\"description\""));
            assertTrue(resultContent.contains("businessSector\":\"sector"));
            assertTrue(resultContent.contains("\"startDate\":\"2028-12-12\""));
            assertTrue(resultContent.contains("\"numberOfSprints\":\"33\""));
            assertTrue(resultContent.contains("budget\":\"11"));
            assertTrue(resultContent.contains("status\":\"PLANNED"));
            //assertEquals(objectMapper.writeValueAsString(projectDTO), resultContent); //O string retornado é diferente
            // apesar de o objecto ser criado corretamente.


            //GET projects/{id}

            MvcResult result2 = mockMvc
                    .perform(MockMvcRequestBuilders.get("/projects/" + "Project_2022_4")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

            String resultContent2 = result2.getResponse().getContentAsString();
            assertNotNull(resultContent2);
            assertTrue(resultContent2.contains("Project_2022_4"));

        }

        @Test
        void shouldReturnNotFound() throws Exception {

            String generatedCode = RandomStringUtils.randomAlphanumeric(10);

            //GET projects/{id}

            MvcResult result = mockMvc
                    .perform(MockMvcRequestBuilders.get("/projects/" + generatedCode)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andReturn();

            String resultContent = result.getResponse().getContentAsString();
            assertNotNull(resultContent);
            assertEquals("{\"errorMessage\":\"Project does not exist\"}", resultContent);
        }

//    @Test
//    void getAllProjectSuccessIntegrationCtrlAndService() {
//        Project test = mock(Project.class);
//        OutputProjectDTO test2 = mock(OutputProjectDTO.class);
//        OutputProjectDTO test3 = mock(OutputProjectDTO.class);
//        List<Project> projects = new ArrayList<>();
//        projects.add(test);
//        CollectionModel<OutputProjectDTO> x = (CollectionModel.of
//                (List.of(new OutputProjectDTO[]{test2, test3})));
//        when(projRepo.findAll()).thenReturn(projects);
//        when(projMapper.toCollectionDto(projects)).thenReturn(x);
//
//        ResponseEntity<?> response = ctrl.showAllProjects();
//
//        assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }

    @Test
    void getAllProjectIntegration() {
        //Arrange
        //Act
        ResponseEntity<?> response = ctrl.showAllProjects();
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    void AgetAllProjectIntegrationSize() {
        //Arrange
        //Act
        CollectionModel<PartialProjectDTO> response = service.getAllProjects();
        int x = response.getContent().size();
        //Assert
        assertEquals(3, x);
    }

    @Test
    void getRequestedProjectIntegration() {
        //Arrange
        //Act
        ResponseEntity<?> response = ctrl.showProjectRequested("Project_2022_1");
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @SneakyThrows
    @Test
    void getRequestedProjectIntegrationSpecific() {
        //Arrange
        //Act
        OutputProjectDTO x = service.showProject("Project_2022_1");
        //Assert
        assertEquals("It doesn't matter", x.businessSector);
        assertEquals("8", x.numberOfSprints);
    }

    @Test
    void ZcreateProjectIntegration() {
        //Arrange
        ProjectDTO x = new ProjectDTO();
        x.projectName = "Ze Manel";
        x.description = "Fazer cozido à Portuguesa";
        x.businessSector = "Gastronomia";
        x.typology = "Fixed cost";
        x.customer = "Antonio";
        x.startDate = "2028-12-12";
        x.budget = "10000";
        x.projectStatus = "Planned";
        x.sprintDuration = "7";
        x.numberOfSprints = "10";
        //Act
        ResponseEntity<?> response = ctrl.createProject(x);
        //Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }

    @SneakyThrows
    @Test
    void CreateProjectIntegrationSize() {
        //Arrange
        ProjectDTO x = new ProjectDTO();
        x.projectName = "Ze Manel";
        x.description = "Fazer cozido à Portuguesa";
        x.businessSector = "Gastronomia";
        x.typology = "Fixed cost";
        x.customer = "Antonio";
        x.startDate = "2028-12-12";
        x.budget = "10000";
        x.projectStatus = "Planned";
        x.sprintDuration = "7";
        x.numberOfSprints = "10";
        //Act
        service.createAndSaveProject(x);
        int xx = service.getAllProjects().getContent().size();
        //Assert
        assertEquals(4, xx);
    }

    @SneakyThrows
    @Test
    void mockMvcTestFindProject() {
        //Arrange
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects/project_2022_4")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
        //Act
        String resultContent = result.getResponse( ).getContentAsString();
        //Assert
        assertNotNull(resultContent);
        assertEquals("{\"errorMessage\":\"Project does not exist\"}", resultContent);
    }

    @SneakyThrows
    @Test
    void mockMvcTestShowAllProjects() {
        //Arrange
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //Act
        String resultContent = result.getResponse( ).getContentAsString();
        //Assert
        assertNotNull(resultContent);
        assertNull(result.getResponse().getErrorMessage());
    }

    @SneakyThrows
    @Test
    void mockMvcTestCreateProject() {
        //Arrange
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.projectName = "name";
        projectDTO.description = "description";
        projectDTO.businessSector = "sector";
        projectDTO.startDate = "2028-12-12";
        projectDTO.sprintDuration = "22";
        projectDTO.numberOfSprints = "33";
        projectDTO.budget = "11";
        projectDTO.typology = "Fixed cost";
        projectDTO.customer = "customer";

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post("/projects")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(projectDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        //Act
        String resultContent = result.getResponse( ).getContentAsString();
        //Assert
        assertNotNull(resultContent);
        assertNull(result.getResponse().getErrorMessage());
    }



//        ProjectDTO projectDTO = new ProjectDTO();
//        TypologyDTO typologyDTO = new TypologyDTO();
//        typologyDTO.description = "fixed cost";
//        projectDTO.projectName = "name";
//        projectDTO.description = "description";
//        projectDTO.businessSector = "sector";
//        projectDTO.startDate = "2028-12-12";
//        projectDTO.sprintDuration = "22";
//        projectDTO.numberOfSprints = "33";
//        projectDTO.budget = "11";
//        projectDTO.typology = "Fixed cost";
//        projectDTO.customer = "customer";


}
