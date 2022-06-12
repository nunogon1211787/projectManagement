package switch2021.project.entities.valueObjects;

import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.ProjectID;
import switch2021.project.entities.valueObjects.vos.ResourceID;
import switch2021.project.entities.valueObjects.vos.UserID;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ResourceIDTest {

    @Test
    void createResourceIDGetUser() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID.getUser(),userID);
    }

    @Test
    void createResourceIDGetProject() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID.getProject(),projectID);
    }

    @Test
    void createResourceIDGetStartDAte() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID.getStartDate(),startDate);
    }

    @Test
    void sameValueAsTrue() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userID,projectID,startDate);
        //Assert
        assertTrue(resourceID.sameValueAs(expected));
    }

    @Test
    void sameValueAsFalse() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        UserID userIDexp = mock(UserID.class);
        ProjectID projectIDexp = mock(ProjectID.class);
        LocalDate startDateexp = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userIDexp,projectIDexp,startDateexp);
        //Assert
        assertFalse(resourceID.sameValueAs(expected));
    }

    @Test
    void equalsFalse() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        UserID userIDexp = mock(UserID.class);
        ProjectID projectIDexp = mock(ProjectID.class);
        LocalDate startDateexp = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userIDexp,projectIDexp,startDateexp);
        //Assert
        assertNotEquals(resourceID,expected);
    }

    @Test
    void equalsNull() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID();
        //Assert
        assertNotEquals(resourceID,expected);
    }

    @Test
    void equalsObject() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertNotEquals(resourceID,userID);
    }

    @Test
    void equalsTrue() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID,expected);
    }

    @Test
    void HashCodeTrue() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        ResourceID expected = new ResourceID(userID,projectID,startDate);
        //Assert
        assertEquals(resourceID.hashCode(),expected.hashCode());
    }

    @Test
    void HashCodeFalse() {
        //Arrange
        UserID userID = mock(UserID.class);
        ProjectID projectID = mock(ProjectID.class);
        LocalDate startDate = LocalDate.parse("2022-10-12");
        //Act
        ResourceID resourceID = new ResourceID(userID,projectID,startDate);
        //Assert
        assertNotEquals(resourceID.hashCode(),projectID.hashCode());
    }
}