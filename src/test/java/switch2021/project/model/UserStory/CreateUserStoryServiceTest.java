package switch2021.project.model.UserStory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.OutPutUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.interfaces.IUserStoryRepo;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.service.CreateUserStoryService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


//@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CreateUserStoryServiceTest {

    @InjectMocks
    CreateUserStoryService createUserStoryService;

    @Mock
    private UserStoryDTO userStoryDTO;
    @Mock
    private IUserStoryFactory iUserStoryFactory;
    @Mock
    private IUserStoryRepo iUserStoryRepo;
    @Mock
    private UserStoryMapper userStoryMapper;
    @Mock
    private UserStory newUserStory;
    @Mock
    private OutPutUsDTO outputUsDto;


    @Test
    @DisplayName("Test to create and save user story - with success")
    public void createAndSaveUserStoryWithSuccessGetProjectID() {

        //Arrange
        when(iUserStoryFactory.createUserStory(userStoryDTO)).thenReturn(newUserStory);
        when(iUserStoryRepo.save(newUserStory)).thenReturn(true);
        when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
        //Act
        OutPutUsDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(userStoryDTO);
        //Assert
        assertEquals(userStoryDTO.projectID, andSaveUserStory.projectID);

    }

    @Test
    @DisplayName("Test to create and save user story - with success")
    public void createAndSaveUserStoryWithSuccessGetUsTitle() {

        //Arrange
        when(iUserStoryFactory.createUserStory(userStoryDTO)).thenReturn(newUserStory);
        when(iUserStoryRepo.save(newUserStory)).thenReturn(true);
        when(userStoryMapper.toDto(newUserStory)).thenReturn(outputUsDto);
        //Act
        OutPutUsDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(userStoryDTO);
        //Assert
        assertEquals(userStoryDTO.title, andSaveUserStory.title);
    }
}