package switch2021.project.controller.old;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import switch2021.project.controller.CreateUserStoryController;
import switch2021.project.dto.OutPutUsDTO;
import switch2021.project.dto.UserStoryDTO;
import switch2021.project.service.CreateUserStoryService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CreateUserStoryControllerTest {


    @InjectMocks
    CreateUserStoryController createUserStoryController;

    @Mock
    UserStoryDTO userStoryDTO;
    @Mock
    CreateUserStoryService createUserStoryService;
    @Mock
    MockHttpServletRequest request;
    @Mock
    OutPutUsDTO outPutUsDTO;


    @Test
    @DisplayName("Test to create a user story - with success")
    public void createUserStory() {
        //Arrange
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(createUserStoryService.createAndSaveUserStory(userStoryDTO)).thenReturn(outPutUsDTO);
        //Act
        ResponseEntity<Object> responseEntity = createUserStoryController.createUserStory(userStoryDTO);
        //Assert
        assertEquals(responseEntity.getStatusCodeValue(), 201);

    }
}


