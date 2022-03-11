package switch2021.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectRoleTest {

//    @Test
//    public void projectRoleConstructorTest() {
//        //Arrange
//        Company company = new Company();
//        //Act
//        ProjectRole role = new ProjectRole("Team Member");
//        //Assert
//        assertEquals(role, company.getProjectRoleStore().getProjectRole("Team Member"));
//    }

    @Test
    public void projectRoleConstructorTestUni() {
        //Arrange
        String roleName = "Team Member";
        //Act
        ProjectRole role = new ProjectRole(roleName);
        //Assert
        assertEquals(role.getName(), roleName);
    }

    @Test
    public void projectRoleConstrutorNull() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            String roleName = "";
            //Act
            ProjectRole role = new ProjectRole(roleName);
        });
    }


    @Test
    public void projectRoleConstructorCopyTest() {
        //Arrange
        Company company = new Company();
        //Act
        ProjectRole role = new ProjectRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        //Assert
        assertEquals(role, company.getProjectRoleStore().getProjectRole("Team Member"));
    }

    @Test
    public void isValidNameTestSuccess() {
        //Arrange
        Company company = new Company();
        //Act and Assert
        assertTrue(company.getProjectRoleStore().getProjectRole("Team Member").isValidName("Team Member"));
    }

    @Test
    public void isValidNameTestSuccessUpperCase() {
        //Arrange
        Company company = new Company();
        //Act and Assert
        assertTrue(company.getProjectRoleStore().getProjectRole("Team Member").isValidName("Team MEMBER"));
    }

    @Test
    public void isValidNameTestFail() {
        //Arrange
        Company company = new Company();
        //Act and Assert
        assertFalse(company.getProjectRoleStore().getProjectRole("Team Member").isValidName("Scrum Master"));
    }
}

