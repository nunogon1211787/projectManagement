package switch2021.project.interfaceAdapters.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dtoModel.dto.CreateUserStoryDTO;

//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import switch2021.project.dtoModel.dto.UserStoryDTO;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
@SpringBootTest
public class UserStoryControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void createNewUserStoryWithSuccess() throws Exception {
//        String userStoryID ="Project_2022_1_As a PO, i want to test this string";
//        String projectID = "Project_2022_1";
//        String title = "As a PO, i want to test this string";
//        int priority = 1;
//        String description = "Make some tests";
//        double timeEstimate = 5.0;
//
//        UserStoryDTO userStoryDTO = new UserStoryDTO(projectID, title, priority, description,timeEstimate);
//
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.post("/userStories")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(userStoryDTO)) // or newCountryInfoMap
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        String resultContent = result.getResponse().getContentAsString();
//        assertNotNull(resultContent);
//        assertEquals(201, result.getResponse().getStatus());
//
//
//    }
//
    @Autowired UserStoryController ctrl;

//    @Test
//    void intTest(){
//
//        String test = "Project_2022_1&As%20fulano%20i%20want%20to%20teste";
//
//        ctrl.showUserStoryRequested(test);
//
//    }

    @Test
    void createAndSaveUserStory() {

        CreateUserStoryDTO dto = new CreateUserStoryDTO("Project_2022_1", "As cenas I want cenas", 1, "cenas fixes", 1000);

        ctrl.createAndSaveUserStory(dto);

    }
}
//
//
