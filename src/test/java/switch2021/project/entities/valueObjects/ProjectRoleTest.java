package switch2021.project.entities.valueObjects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.*;

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


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestEquals() {
        //Arrange
        ProjectRole projectRole = new ProjectRole("Project Role");
        ProjectRole projectRole1 = new ProjectRole("Project Role");
        //Assert
        assertEquals(projectRole, projectRole1);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestNotEquals() {
        //Arrange
        ProjectRole projectRole = new ProjectRole("Project Role");
        ProjectRole projectRole1 = new ProjectRole("Project Role 1");
        //Assert
        assertNotEquals(projectRole, projectRole1);
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void overrideTestClass() {
        //Arrange
        ProjectRole projectRole = new ProjectRole("Project Role");
        Description description = new Description("Role Project");
        //Assert
        assertNotEquals(projectRole, description);
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTest() {
        //Arrange
        ProjectRole projectRole = new ProjectRole("Project Role");
        ProjectRole projectRole1 = new ProjectRole("Project Role");
        //Assert
        assertEquals(projectRole.hashCode(), projectRole1.hashCode());
    }


    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestFail() {
        //Arrange
        ProjectRole projectRole = new ProjectRole("Project Role");
        ProjectRole projectRole1 = new ProjectRole("Role Project");
        //Assert
        assertNotEquals(projectRole.hashCode(), projectRole1.hashCode());
    }

    @DisplayName("Test override conditions for coverage purposes")
    @Test
    public void hashCodeTestClassFail() {
        //Arrange
        ProjectRole projectRole = new ProjectRole("Project Role");
        Description description = new Description("Role Project");
        //Assert
        assertNotEquals(projectRole.hashCode(), description.hashCode());
    }


}

