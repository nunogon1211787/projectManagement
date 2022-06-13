package switch2021.project.interfaceAdapters.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import switch2021.project.applicationServices.service.SprintService;
import switch2021.project.dtoModel.dto.NewSprintDTO;
import switch2021.project.dtoModel.dto.OutputSprintDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class SprintControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SprintService sprintService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    public void shouldCreateSprint() throws Exception {
//
//        String projectID = "Project_2022_1";
//        String sprintName = "Sprint Name";
//
//        NewSprintDTO newSprintDTO = new NewSprintDTO(projectID, sprintName);
//
//        OutputSprintDTO outputSprintDTO = new OutputSprintDTO();
//
//        MvcResult result = mockMvc
//                .perform(MockMvcRequestBuilders.post("/sprints"))
//                .andDo(sprintService.createAndSaveSprint(newSprintDTO))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        String content = result.getResponse().getContentAsString();
//
//        assertNotNull(content);
//        assertEquals(sprintService.createAndSaveSprint(newSprintDTO), outputSprintDTO);
//    }

}
