package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.OutputStreamFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.applicationServices.service.ProjectService;
import switch2021.project.dtoModel.dto.ErrorMessage;
import switch2021.project.dtoModel.dto.OutputProjectDTO;
import switch2021.project.dtoModel.dto.ProjectDTO;
import switch2021.project.dtoModel.dto.TypologyDTO;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    ProjectController ctrl;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ProjectService service;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {

        ProjectDTO test = new ProjectDTO();

        test.projectName = "Project 2";
        test.description = "Isto é um projecto test";
        test.businessSector = "IT";
        test.typology = "costfix";
        test.customer = "Internal";
        test.startDate = "2022-05-27";
        test.endDate = "2022-06-27";
        test.numberOfSprints = "1";
        test.budget = "1000";
        test.sprintDuration = "7";

        ctrl.createProject(test);
    }

    @Test
    void shouldReturnNewProjectAndOk() throws Exception {
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

        MvcResult resulttypo = mockMvc
                .perform(MockMvcRequestBuilders.post("/typologies")
                                 .contentType("application/json")
                                 .content(objectMapper.writeValueAsString(typologyDTO))
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

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
                .perform(MockMvcRequestBuilders.get("/projects/" + "Project_2022_1")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent2 = result2.getResponse().getContentAsString();
        assertNotNull(resultContent2);
        assertTrue(resultContent2.contains("Project_2022_1"));
    }

    @Test
    void shouldReturnNotFound() throws Exception {

        String generatedCode = RandomStringUtils.randomAlphanumeric(10);

        //GET projects/{id}

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/projects/" + generatedCode)
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertEquals("{\"errorMessage\":\"Project does not exist\"}", resultContent);
    }

    @Test
    void getAllProjectSuccess() {

        OutputProjectDTO test = mock(OutputProjectDTO.class);
        OutputProjectDTO test2 = mock(OutputProjectDTO.class);
        OutputProjectDTO test3 = mock(OutputProjectDTO.class);

        when(service.showAllProjects()).thenReturn(CollectionModel.of(List.of(new OutputProjectDTO[]{test, test2, test3})));
        ResponseEntity<?> response = ctrl.showAllProjects();

        assertThat(response.getStatusCodeValue()).isEqualTo(200);


    }
}
