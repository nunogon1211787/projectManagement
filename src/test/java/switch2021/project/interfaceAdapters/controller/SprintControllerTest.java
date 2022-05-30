package switch2021.project.interfaceAdapters.controller;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SprintControllerTest {
/*
    @Autowired
    private SprintController sprintController;

    @Test //Integration Test
    void itShouldRegisterAUserIntegration() {
        //Arrange
        NewSprintDTO dto = new NewSprintDTO();
        dto.projectID = "maneloliveira";
        dto.name = "maneloliveira@beaver.com";
        //Act
        ResponseEntity<Object> responseEntity = sprintController.createAndSaveSprint(dto);
        //Assert
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
    
 */


//    @InjectMocks
//    CreateSprintController createSprintController;
//
//    @Mock
//    SprintService createSprintService;
//    @Mock
//    NewSprintDTO newSprintDTO;
//    @Mock
//    OutputSprintDTO outPutSprintDTO;
//    @Mock
//    MockHttpServletRequest mockHttpServletRequest;
//
//
//    @Test
//    @DisplayName("Test to create a sprint, with success")
//    public void createSprint_Success() {
//
//        // Arrange
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockHttpServletRequest));
//        when(createSprintService.createAndSaveSprint(newSprintDTO)).thenReturn(outPutSprintDTO);
//
//        //Act
//        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(newSprintDTO);
//
//        //Assert
//        assertEquals(responseEntity.getStatusCodeValue(), 201);
//    }

//    @Test
//    @DisplayName("Create Sprint IT")
//    public void createSprint(){
//        //Arrange
//        String project = "Project_2022_1";
//        String name = "Sprint Name";
//        NewSprintDTO dto = new NewSprintDTO(project, name);
//        //Act
//        ResponseEntity<Object> responseEntity = createSprintController.createAndSaveSprint(dto);
//        //Assert
//        assertEquals(201, responseEntity.getStatusCodeValue());
//    }

}
