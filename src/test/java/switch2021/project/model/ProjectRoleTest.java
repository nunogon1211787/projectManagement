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
        assertEquals(role.getName().getDescriptionF(), roleName);
    }

    @Test
    public void projectRoleConstructorEmpty() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            String roleName = "";
            //Act
            ProjectRole role = new ProjectRole(roleName);
        });
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

