package switch2021.project.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.OutputUserStoryDTO;
import switch2021.project.dto.CreateUserStoryDTO;
import switch2021.project.dto.UserStoryIdDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.interfaces.IUserStoryRepo;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.model.UserStory.UserStory;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;



@ExtendWith(SpringExtension.class)
public class UserStoryServiceTest {

    @InjectMocks
    UserStoryService createUserStoryService;

    @Mock
    private CreateUserStoryDTO createUserStoryDTO;
    @Mock
    private IUserStoryFactory iUserStoryFactory;
    @Mock
    private IUserStoryRepo iUserStoryRepo;
    @Mock
    private UserStoryMapper userStoryMapper;
    @Mock
    private UserStory newUserStory;
    @Mock
    private OutputUserStoryDTO outputUsDto;


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

    @Test
    @DisplayName("Test to create and save user story - with success")
    public void createAndSaveUserStoryWithSuccessGetPriority() {

        //Arrange
        when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
        when(iUserStoryRepo.save(newUserStory)).thenReturn(true);
        when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
        //Act
        OutputUserStoryDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(createUserStoryDTO);
        //Assert
        assertEquals(createUserStoryDTO.getPriority(), andSaveUserStory.getPriority());
    }

    @Test
    @DisplayName("Test to create and save user story - with success")
    public void createAndSaveUserStoryWithSuccessGetDescription() {

        //Arrange
        when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
        when(iUserStoryRepo.save(newUserStory)).thenReturn(true);
        when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
        //Act
        OutputUserStoryDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(createUserStoryDTO);
        //Assert
        assertEquals(createUserStoryDTO.getDescription(), andSaveUserStory.getDescription());
    }

    @Test
    @DisplayName("Test to create and save user story - with success")
    public void createAndSaveUserStoryFailRepeated() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            when(iUserStoryFactory.createUserStory(createUserStoryDTO)).thenReturn(newUserStory);
            when(iUserStoryRepo.save(newUserStory)).thenReturn(false);
            when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
            //Act
           createUserStoryService.createAndSaveUserStory(createUserStoryDTO);
        });
    }
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