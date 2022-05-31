package switch2021.project.applicationServices.service;

import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;


@SpringBootTest
public class UserStoryServiceTest {
//
//    @Autowired
//    UserStoryService createUserStoryService;

//    @Mock
//    private CreateUserStoryDTO createUserStoryDTO;
//    @Mock
//    private IUserStoryFactory iUserStoryFactory;
//    @Mock
//    private IUserStoryRepo iUserStoryRepo;
//    @Mock
//    private UserStoryMapper userStoryMapper;
//    @Mock
//    private UserStory newUserStory;
//    @Mock
//    private OutputUserStoryDTO outputUsDto;

//    @Test
//    void createAndSaveUserStory() throws Exception {
//
//        CreateUserStoryDTO dto = new CreateUserStoryDTO("Project_2022_1","As director I want cenas", 1, "create project", 1000);
//
//        createUserStoryService.createAndSaveUserStory(dto);
//
//    }


//    @Test
//    @DisplayName("Test to create and save user story - with success")
//    public void createAndSaveUserStoryWithSuccessGetProjectID() {
//
//        //Arrange
//        when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
//        when(iUserStoryRepo.save(newUserStory)).thenReturn(true);
//        when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
//        UserStoryIdDTO usIdDto = mock(UserStoryIdDTO.class);
//        when(outputUsDto.getUserStoryID()).thenReturn(usIdDto);
//        //Act
//        OutputUserStoryDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(createUserStoryDTO);
//        //Assert
//        assertEquals(createUserStoryDTO.projectID, andSaveUserStory.getUserStoryID().projectID);
//
//    }
//
//    @Test
//    @DisplayName("Test to create and save user story - with success")
//    public void createAndSaveUserStoryWithSuccessGetUsTitle() {
//
//        //Arrange
//        when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
//        when(iUserStoryRepo.save(newUserStory)).thenReturn(true);
//        when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
//        UserStoryIdDTO usIdDto = mock(UserStoryIdDTO.class);
//        when(outputUsDto.getUserStoryID()).thenReturn(usIdDto);
//        //Act
//        OutputUserStoryDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(createUserStoryDTO);
//        //Assert
//        assertEquals(createUserStoryDTO.title, andSaveUserStory.getUserStoryID().title);
//    }

//    @Test
//    @DisplayName("Test to create and save user story - with success")
//    public void createAndSaveUserStoryWithSuccessGetPriority() {
//
//        //Arrange
//        when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
//        when(iUserStoryRepo.save(newUserStory)).thenReturn(true);
//        when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
//        //Act
//        OutputUserStoryDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(createUserStoryDTO);
//        //Assert
//        assertEquals(createUserStoryDTO.getPriority(), andSaveUserStory.getPriority());
//    }
//
//    @Test
//    @DisplayName("Test to create and save user story - with success")
//    public void createAndSaveUserStoryWithSuccessGetDescription() {
//
//        //Arrange
//        when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
//        when(iUserStoryRepo.save(newUserStory)).thenReturn(true);
//        when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
//        //Act
//        OutputUserStoryDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(createUserStoryDTO);
//        //Assert
//        assertEquals(createUserStoryDTO.getDescription(), andSaveUserStory.getDescription());
//    }

//    @Test
//    @DisplayName("Test to create and save user story - with success")
//    public void createAndSaveUserStoryFailRepeated() {
//        //Assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            //Arrange
//            when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
//            when(iUserStoryRepo.save(newUserStory)).thenReturn(false);
//            when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
//            //Act
//           createUserStoryService.createAndSaveUserStory(createUserStoryDTO);
//        });
//    }
//
//    @Test
//    void showAUserStory() throws Exception {
//
//        CreateUserStoryService srv = new CreateUserStoryService();
//
//        String id = "Project_2022_1&As%20fulano%20i%20want%20to%20teste";
//
//        srv.showAUserStory(id);
//
//
//    }
}