package switch2021.project.model.UserStory;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import switch2021.project.factoryInterface.IUserStoryFactory;
import switch2021.project.interfaces.IRepoUserStory;
import switch2021.project.mapper.UserStoryMapper;
import switch2021.project.service.CreateUserStoryService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateUserStoryServiceTest {

    @InjectMocks
    CreateUserStoryService createUserStoryService;

    @Mock
    private IRepoUserStory iRepoUserStory;
    @Mock
    private UserStoryMapper userStoryMapper;
    @Mock
    private IUserStoryFactory iUserStoryFactory;


//    @Test
//    public void createAndSaveUserStory() {
//
//        //Arrange
//        UserStoryDTO userStoryDTO = new UserStoryDTO();
//        userStoryDTO.userStoryID = "Project_2022_1_As a PO, i want to test this string";
//        userStoryDTO.projectID = "Project_2022_1";
//        userStoryDTO.title = "As a PO, i want to test this string";
//        userStoryDTO.priority = 1;
//        userStoryDTO.description = "Default Story";
//        userStoryDTO.timeEstimate = 5.0;
//
//
//        OutputUsDTO outputUserStoryDTO = new OutputUsDTO();
//        outputUserStoryDTO.userStoryID = "Project_2022_1_As a PO, i want to test this string";
//        outputUserStoryDTO.projectID = "Project_2022_1";
//        outputUserStoryDTO.title = "As a PO, i want to test this string";
//
//        //Act
//        UserStory us = new UserStory(userStoryDTO.projectID, userStoryDTO.userStoryID,
//                userStoryDTO.title, userStoryDTO.priority, userStoryDTO.description, userStoryDTO.timeEstimate);
//
//        when(iUserStoryFactory.createUserStory(userStoryDTO.projectID, userStoryDTO.userStoryID,
//                userStoryDTO.title, userStoryDTO.priority, userStoryDTO.description, userStoryDTO.timeEstimate)).thenReturn(us);
//
//        when(iRepoUserStory.save(any(UserStory.class))).thenReturn(true);
//
//        when(userStoryMapper.toDto(us)).thenReturn(outputUserStoryDTO);
//
//        OutputUsDTO andSaveUserStory = createUserStoryService.createAndSaveUserStory(userStoryDTO);
//
//        //Assert
//        assertEquals(outputUserStoryDTO.userStoryID, andSaveUserStory.getUserStoryID());
//        assertEquals(outputUserStoryDTO.projectID, andSaveUserStory.getProjectID());
//        assertEquals(outputUserStoryDTO.title, andSaveUserStory.getTitle());
//    }
}

