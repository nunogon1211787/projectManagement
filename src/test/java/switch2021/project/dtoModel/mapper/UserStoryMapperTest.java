package switch2021.project.dtoModel.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dtoModel.dto.OutputUserStoryDTO;
import switch2021.project.entities.aggregates.UserStory.*;
import switch2021.project.entities.valueObjects.vos.*;

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

        OutputUserStoryDTO outputUserStoryDTO = userStoryMapper.toDto(newUserStory);

        //Assert
//        assertEquals(newUserStory.getUserStoryID().toString(), outputUserStoryDTO.getUserStoryID().toString());
        assertEquals(newUserStory.getPriority().getPriorityUs(), outputUserStoryDTO.priority);
        assertEquals(newUserStory.getDescription().getText(), outputUserStoryDTO.description);
        assertEquals(newUserStory.getTimeEstimate().getUsHours(), outputUserStoryDTO.timeEstimate);
    }

//    @Test
//    @DisplayName("Transform newUserStory into DTO")
//    public void ToDtoNewUserStoryProjectID() {
//        //Arrange
//        UserStoryMapper userStoryMapper = new UserStoryMapper();
//        UserStory newUserStory = mock(UserStory.class);
//        UserStoryID userStoryID = mock(UserStoryID.class);
//        Description description = mock(Description.class);
//        ProjectID projectID = mock(ProjectID.class);
//        UsTitle usTitle = mock(UsTitle.class);
//        UsPriority usPriority = mock(UsPriority.class);
//        UsHour timeEstimate = mock(UsHour.class);
//        //Act
//        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
//        when(userStoryID.getProjectID()).thenReturn(projectID);
//        when(projectID.getCode()).thenReturn("Project_2022_1");
//        when(userStoryID.getUsTitle()).thenReturn(usTitle);
//        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
//        when(newUserStory.getPriority()).thenReturn(usPriority);
//        when(usPriority.getPriorityUs()).thenReturn(1);
//        when(newUserStory.getDescription()).thenReturn(description);
//        when(description.getText()).thenReturn("Make Test");
//        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
//        when(timeEstimate.getUsHours()).thenReturn(5.0);
//
//        OutputUserStoryDTO outputUserStoryDTO = userStoryMapper.toDto(newUserStory);
//
//        //Assert
//        assertEquals(projectID.getCode(), outputUserStoryDTO.userStoryID.projectID);
//
//    }
//
//    @Test
//    @DisplayName("Transform newUserStory into DTO")
//    public void ToDtoNewUserStoryUsTitle() {
//        //Arrange
//        UserStoryMapper userStoryMapper = new UserStoryMapper();
//        UserStory newUserStory = mock(UserStory.class);
//        UserStoryID userStoryID = mock(UserStoryID.class);
//        Description description = mock(Description.class);
//        ProjectID projectID = mock(ProjectID.class);
//        UsTitle usTitle = mock(UsTitle.class);
//        UsPriority usPriority = mock(UsPriority.class);
//        UsHour timeEstimate = mock(UsHour.class);
//        //Act
//        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
//        when(userStoryID.getProjectID()).thenReturn(projectID);
//        when(projectID.getCode()).thenReturn("Project_2022_1");
//        when(userStoryID.getUsTitle()).thenReturn(usTitle);
//        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
//        when(newUserStory.getPriority()).thenReturn(usPriority);
//        when(usPriority.getPriorityUs()).thenReturn(1);
//        when(newUserStory.getDescription()).thenReturn(description);
//        when(description.getText()).thenReturn("Make Test");
//        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
//        when(timeEstimate.getUsHours()).thenReturn(5.0);
//
//        OutputUserStoryDTO outputUserStoryDTO = userStoryMapper.toDto(newUserStory);
//
//        //Assert
//        assertEquals(usTitle.getTitleUs(), outputUserStoryDTO.userStoryID.title);
//    }
//
//    @Test
//    @DisplayName("Validate data from outputDTO")
//    public void getInfoFromOutPutUsDTO() {
//        //Arrange
//        UserStoryMapper userStoryMapper = new UserStoryMapper();
//        UserStory newUserStory = mock(UserStory.class);
//        UserStoryID userStoryID = mock(UserStoryID.class);
//        Description description = mock(Description.class);
//        ProjectID projectID = mock(ProjectID.class);
//        UsTitle usTitle = mock(UsTitle.class);
//        UsPriority usPriority = mock(UsPriority.class);
//        UsHour timeEstimate = mock(UsHour.class);
//        //Act
//        when(newUserStory.getUserStoryID()).thenReturn(userStoryID);
//        when(userStoryID.getProjectID()).thenReturn(projectID);
//        when(projectID.getCode()).thenReturn("Project_2022_1");
//        when(userStoryID.getUsTitle()).thenReturn(usTitle);
//        when(usTitle.getTitleUs()).thenReturn("As a PO, i want to test this string");
//        when(newUserStory.getPriority()).thenReturn(usPriority);
//        when(usPriority.getPriorityUs()).thenReturn(1);
//        when(newUserStory.getDescription()).thenReturn(description);
//        when(description.getText()).thenReturn("Make Test");
//        when(newUserStory.getTimeEstimate()).thenReturn(timeEstimate);
//        when(timeEstimate.getUsHours()).thenReturn(5.0);
//
//        OutputUserStoryDTO outputUserStoryDTO = userStoryMapper.toDto(newUserStory);
//
//        //Assert
////        assertEquals(outputUserStoryDTO.getUserStoryID().toString(),userStoryID.toString() );
//        assertEquals(outputUserStoryDTO.getUserStoryID().projectID,"Project_2022_1" );
//        assertEquals(outputUserStoryDTO.getUserStoryID().title,"As a PO, i want to test this string" );
//        assertEquals(outputUserStoryDTO.getPriority(),1 );
//        assertEquals(outputUserStoryDTO.getDescription(),"Make Test" );
//        assertEquals(outputUserStoryDTO.getTimeEstimate(),5.0 );
//    }

}

