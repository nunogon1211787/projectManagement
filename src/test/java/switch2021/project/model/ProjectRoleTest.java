package switch2021.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectRoleTest {

    @Test
    public void projectRoleConstructorSuccess() {
        //Arrange
        String roleName = "Team Member";
        //Act
        ProjectRole role = new ProjectRole(roleName);
        //Assert
        assertEquals(role.getName(), roleName);
    }

    @Test
    public void projectRoleConstructorNull() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String roleName = "";
            //Act
            ProjectRole role = new ProjectRole(roleName);
        });
    }

    @Test
    public void isValidNameTestSuccess() {
        //Arrange
        String roleName = "Team Member";
        //Act and Assert
        ProjectRole role = new ProjectRole(roleName);
        assertTrue(role.isValidName("Team Member"));
    }

    @Test
    public void isValidNameTestSuccessUpperCase() {
        //Arrange
        String roleName = "Team Member";
        //Act and Assert
        ProjectRole role = new ProjectRole(roleName);
        assertTrue(role.isValidName("Team MEMBER"));
    }

    @Test
    public void isValidNameTestFail() {
        //Arrange
        String roleName = "Scrum Master";
        //Act and Assert
        ProjectRole role = new ProjectRole(roleName);
        assertFalse(role.isValidName("Team Member"));
    }

    @Test
    public void projectRoleNull() {
        //Arrange
        String roleName = "Team Member";
        //Act
        ProjectRole role = new ProjectRole(roleName);
        ProjectRole roleNull = null;
        //Assert
        assertNotEquals(role, roleNull);
    }
}

