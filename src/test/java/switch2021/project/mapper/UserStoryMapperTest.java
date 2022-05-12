package switch2021.project.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.OutPutUsDTO;
import switch2021.project.model.UserStory.*;
import switch2021.project.model.valueObject.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserStoryMapperTest {


    @Test
    @DisplayName("Transform newUserStory into DTO")
    public void ToDtoNewUserStory() {
        //Arrange
        UserStoryMapper userStoryMapper = new UserStoryMapper();
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        //Act
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");

        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        OutPutUsDTO outPutUsDTO = userStoryMapper.toDto(newUserStory);

        //Assert
        assertEquals(newUserStory.getUserStoryID().toString(), outPutUsDTO.userStoryID);
        assertEquals(newUserStory.getPriority().getPriorityUs(), outPutUsDTO.priority);
        assertEquals(newUserStory.getDescription().getText(), outPutUsDTO.description);
        assertEquals(newUserStory.getTimeEstimate().getUsHours(), outPutUsDTO.timeEstimate);
    }

    @Test
    @DisplayName("Transform newUserStory into DTO")
    public void ToDtoNewUserStoryProjectID() {
        //Arrange
        UserStoryMapper userStoryMapper = new UserStoryMapper();
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        //Act
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        OutPutUsDTO outPutUsDTO = userStoryMapper.toDto(newUserStory);

        //Assert
        assertEquals(projectID.getCode(), outPutUsDTO.projectID);

    }

    @Test
    @DisplayName("Transform newUserStory into DTO")
    public void ToDtoNewUserStoryUsTitle() {
        //Arrange
        UserStoryMapper userStoryMapper = new UserStoryMapper();
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        //Act
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        OutPutUsDTO outPutUsDTO = userStoryMapper.toDto(newUserStory);

        //Assert
        assertEquals(usTitle.getTitleUs(), outPutUsDTO.title);
    }

    @Test
    @DisplayName("Validate data from outputDTO")
    public void getInfoFromOutPutUsDTO() {
        //Arrange
        UserStoryMapper userStoryMapper = new UserStoryMapper();
        UserStory newUserStory = mock(UserStory.class);
        UserStoryID userStoryID = mock(UserStoryID.class);
        Description description = mock(Description.class);
        ProjectID projectID = mock(ProjectID.class);
        UsTitle usTitle = mock(UsTitle.class);
        UsPriority usPriority = mock(UsPriority.class);
        UsHour timeEstimate = mock(UsHour.class);
        //Act
        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
        when(userStoryID.getProjectID()).thenReturn(projectID);
        when(projectID.getCode()).thenReturn("Project_2022_1");
        when(userStoryID.getUsTitle()).thenReturn(usTitle);
        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
        when(newUserStory.getPriority()).thenReturn(usPriority);
        when(usPriority.getPriorityUs()).thenReturn(1);
        when(newUserStory.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Make Test");
        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
        when(timeEstimate.getUsHours()).thenReturn(5.0);

        OutPutUsDTO outPutUsDTO = userStoryMapper.toDto(newUserStory);

        //Assert
        assertEquals(outPutUsDTO.getUserStoryID(),userStoryID.toString() );
        assertEquals(outPutUsDTO.getProjectID(),"Project_2022_1" );
        assertEquals(outPutUsDTO.getTitle(),"As a PO, i want to test this string" );
        assertEquals(outPutUsDTO.getPriority(),1 );
        assertEquals(outPutUsDTO.getDescription(),"Make Test" );
        assertEquals(outPutUsDTO.getTimeEstimate(),5.0 );
    }

}

