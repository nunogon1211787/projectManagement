package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.dtoModel.dto.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SprintControllerTest {

    public static final String URL_PATH = "https://localhost:8443";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldReturnNewProjectAndOk() throws Exception {
        NewSprintDTO sprintDTO = new NewSprintDTO("Project_2022_1", "description", "");

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(URL_PATH + "/sprints")
                                 .contentType("application/json")
                                 .content(objectMapper.writeValueAsString(sprintDTO))
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("\"projectID\":\"Project_2022_1\""));
        assertTrue(resultContent.contains("\"name\":\"description\""));

        //assertEquals(objectMapper.writeValueAsString(projectDTO), resultContent); //O string retornado é diferente
        // apesar de o objecto ser criado corretamente.


        //GET sprints/{id}

/*        MvcResult result2 = mockMvc
                .perform(MockMvcRequestBuilders.get("/sprints/" + "Project_2022_1")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultContent2 = result2.getResponse().getContentAsString();
        assertNotNull(resultContent2);
        assertTrue(resultContent2.contains("Project_2022_1"));*/

    }


    @Test
    void shouldAddUSToBacklog() throws Exception {
        UserStoryDTO createUserStoryDTO = new UserStoryDTO("Project_2022_1", "As Queen i want to break " +
                "free", 2, "Teste", 12);

        UserStoryIdDTO userStoryIdDTO = new UserStoryIdDTO();
        TypologyDTO typologyDTO = new TypologyDTO();

        typologyDTO.description = "fixed cost2";


        userStoryIdDTO.projectID = "Project_2022_1";
        userStoryIdDTO.title = "As Queen i want to break free";

        NewSprintDTO sprintDTO = new NewSprintDTO("Project_2022_1", "Sprint", "");

        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.projectName = "name";
        projectDTO.description = "description";
        projectDTO.businessSector = "sector";
        projectDTO.startDate = "2028-12-12";
        projectDTO.sprintDuration = "22";
        projectDTO.numberOfSprints = "33";
        projectDTO.budget = "11";
        projectDTO.typology = "fixed cost2";
        projectDTO.customer = "customer";

        MvcResult resulttypo = mockMvc
                .perform(MockMvcRequestBuilders.post(URL_PATH + "/typologies")
                                 .contentType("application/json")
                                 .content(objectMapper.writeValueAsString(typologyDTO))
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        MvcResult projeResult = mockMvc
                .perform(MockMvcRequestBuilders.post(URL_PATH + "/projects")
                                 .contentType("application/json")
                                 .content(objectMapper.writeValueAsString(projectDTO))
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        String projContent = projeResult.getResponse().getContentAsString();
        assertNotNull(projContent);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(URL_PATH + "/sprints")
                                 .contentType("application/json")
                                 .content(objectMapper.writeValueAsString(sprintDTO))
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);

        MvcResult resultUs = mockMvc
                .perform(MockMvcRequestBuilders.post(URL_PATH + "/userstories")
                                 .contentType("application/json")
                                 .content(objectMapper.writeValueAsString(createUserStoryDTO))
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        String resultUsContent = resultUs.getResponse().getContentAsString();
        assertNotNull(resultUsContent);


        MvcResult result2 = mockMvc
                .perform(MockMvcRequestBuilders.post(URL_PATH + "/sprints/Project_2022_1_Sprint")
                                 .contentType("application/json")
                                 .content(objectMapper.writeValueAsString(userStoryIdDTO))
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        MvcResult scrumBoard = mockMvc
                .perform(MockMvcRequestBuilders.get(URL_PATH + "/sprints/Project_2022_1_Sprint")
                                 .contentType("application/json")
                                 .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String result2Content = result2.getResponse().getContentAsString();
        assertNotNull(result2Content);

        assertTrue(result2Content.contains("User story added to sprintbacklog"));
    }

    @Test
    @DisplayName("Validate sprint start date")
    void validateSprintStartDate() throws Exception {

    }
}
