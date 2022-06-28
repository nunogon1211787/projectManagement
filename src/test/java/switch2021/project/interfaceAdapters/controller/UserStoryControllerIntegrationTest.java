package switch2021.project.interfaceAdapters.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.applicationServices.service.UserStoryService;
import switch2021.project.dtoModel.dto.UserStoryDTO;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserStoryControllerIntegrationTest {
    public static final String BASE_URL = "https://localhost:8443";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserStoryService userStoryService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateUserStory() throws Exception {
        //Arrange
        String projectID = "Project_2022_3";
        String title = "As PO, i want to test";
        int priority = 5;
        String description = "make this work";
        double timeEstimate = 10.0;

        UserStoryDTO userStoryDTO = new UserStoryDTO(projectID, title, priority, description, timeEstimate);
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(BASE_URL + "/userstories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userStoryDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())//Assert
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertNotNull(resultContent);
        assertTrue(resultContent.contains("\"projectID\":\"Project_2022_3\""));
        assertTrue(resultContent.contains("\"usTitle\":\"As PO, i want to test\""));
    }

    @Test
    void shouldNotCreateUserStory() throws Exception {
        //Arrange
        String projectID = "Project_2022_3";
        String title = "As PO, i want to test";
        int priority = 6; // doesn't exist
        String description = "make this work";
        double timeEstimate = 10.0;

        UserStoryDTO userStoryDTO = new UserStoryDTO(projectID, title, priority, description, timeEstimate);
        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL + "/userstories")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(userStoryDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())//Assert
                .andReturn();
    }


    @Test
    void shouldGetUserStory() throws Exception {
        //Arrange
        String projectID = "Project_2022_1";
        String title = "as want US01";

        String id = projectID + "&" + title;
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/userstories/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("id\":\"Project_2022_1&as want US01"));
    }

    @Test
    void shouldNotGetUserStory() throws Exception {
        //Arrange
        String projectID = "Project_2022_1";
        String title = "as want ZZZZ";
        String id = projectID + "&" + title;
        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL + "/userstories/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void shouldGetAllUserStories() throws Exception {
        //Act
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(BASE_URL + "/userstories/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        //Assert
        String resultContent = result.getResponse().getContentAsString();
        assertTrue(resultContent.contains("id\":\"Project_2022_1&as want US01\""));
        assertTrue(resultContent.contains("id\":\"Project_2022_2&as want US031\""));
    }

}






