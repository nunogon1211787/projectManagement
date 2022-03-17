package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProjectRoleTest {

    @Test
    public void projectRoleConstructorSuccess() {
        //Arrange
        String roleName = "Team Member";
        //Act
        ProjectRole role = new ProjectRole(roleName);
        //Assert
        assertEquals(role.getName().getText(), roleName);
    }

    @Test
    public void projectRoleConstructorSuccess2() {

        //Arrange
        String roleName = "Team Member";
        Description name = mock( Description.class);
        when(name.getText()).thenReturn("Team Member");
        ProjectRole role = new ProjectRole(roleName);
        assertEquals(name.getText(), role.getName().getText());
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

