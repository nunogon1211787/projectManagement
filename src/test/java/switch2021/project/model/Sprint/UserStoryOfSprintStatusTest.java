package switch2021.project.model.Sprint;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.valueObject.UserStoryOfSprintStatus;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class UserStoryOfSprintStatusTest {

    @DisplayName("Test to compare two lists of all user story of sprint status values")
    @Test
    void getUserStoryOfSprintStatusDescription() {
        //Act
        List<String> statusList1 = UserStoryOfSprintStatus.getUserStoryOfSprintStatus();
        List<String> statusList2 = List.of(new String[]{"Todo", "InProgress", "Done", "Cancelled"});
        //Assert
        assertEquals(statusList1, statusList2 );
    }

    @DisplayName("Test check the size of the list of all user story of sprint status values")
    @Test
    void getUserStoryOfSprintStatusDescription_Size() {
        //Act
        List<String> statusList = UserStoryOfSprintStatus.getUserStoryOfSprintStatus();
        //Assert
        assertEquals(4, statusList.size() );
    }

    @DisplayName("Test to get a specific values - To do")
    @Test
    void value_Todo() {
        //Act
        UserStoryOfSprintStatus[] status = UserStoryOfSprintStatus.values();
        UserStoryOfSprintStatus x = status[0];
        //Assert
        assertEquals(x, UserStoryOfSprintStatus.Todo);
    }

    @DisplayName("Test to get a specific values - In Progress")
    @Test
    void value_InProgress() {
        //Act
        UserStoryOfSprintStatus[] status = UserStoryOfSprintStatus.values();
        UserStoryOfSprintStatus x = status[1];
        //Assert
        assertEquals(x, UserStoryOfSprintStatus.InProgress);
    }

    @DisplayName("Test to get a specific values - Done")
    @Test
    void value_Done() {
        //Act
        UserStoryOfSprintStatus[] status = UserStoryOfSprintStatus.values();
        UserStoryOfSprintStatus x = status[2];
        //Assert
        assertEquals(x, UserStoryOfSprintStatus.Done);
    }

    @DisplayName("Test to get a specific values - Cancelled")
    @Test
    void value_Cancelled() {
        //Act
        UserStoryOfSprintStatus[] status = UserStoryOfSprintStatus.values();
        UserStoryOfSprintStatus x = status[3];
        //Assert
        assertEquals(x, UserStoryOfSprintStatus.Cancelled);
    }

    @DisplayName("Test to get a specific values - Fail")
    @Test
    void value_Fail() {
        //Act
        UserStoryOfSprintStatus[] status = UserStoryOfSprintStatus.values();
        UserStoryOfSprintStatus x = status[1];
        //Assert
        assertNotEquals(x, UserStoryOfSprintStatus.Todo);
    }

    @DisplayName("Test with ValueOf - Todo")
    @Test
    void valueOf_Todo() {
        //Act
        UserStoryOfSprintStatus x = UserStoryOfSprintStatus.valueOf("Todo");
        //Assert
        assertEquals(x, UserStoryOfSprintStatus.Todo);
    }

    @DisplayName("Test with ValueOf - In Progress")
    @Test
    void valueOf_InProgress() {
        //Act
        UserStoryOfSprintStatus x = UserStoryOfSprintStatus.valueOf("InProgress");
        //Assert
        assertEquals(x, UserStoryOfSprintStatus.InProgress);
    }

    @DisplayName("Test with ValueOf - Done")
    @Test
    void valueOf_Done() {
        //Act
        UserStoryOfSprintStatus x = UserStoryOfSprintStatus.valueOf("Done");
        //Assert
        assertEquals(x, UserStoryOfSprintStatus.Done);
    }

    @DisplayName("Test with ValueOf - Cancelled")
    @Test
    void valueOf_Cancelled() {
        //Act
        UserStoryOfSprintStatus x = UserStoryOfSprintStatus.valueOf("Cancelled");
        //Assert
        assertEquals(x, UserStoryOfSprintStatus.Cancelled);
    }

    @DisplayName("Test with ValueOf - Fail")
    @Test
    void valueOf_Fail() {
        //Act
        UserStoryOfSprintStatus x = UserStoryOfSprintStatus.valueOf("Cancelled");
        //Assert
        assertNotEquals(x, UserStoryOfSprintStatus.Todo);
    }
}
