package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.model.ProjectRole;
import static org.junit.jupiter.api.Assertions.*;

class ProjectRoleStoreTest {

    @Test
    public void projectRoleStoreConstructorTest() {
        //Arrange
        Company company = new Company();
        //Act
        ProjectRoleStore test = new ProjectRoleStore();
        test.populateDefault();
        //Assert
        assertEquals(test.getProjectRoleList(),company.getProjectRoleStore().getProjectRoleList());
    }

    @Test
    public void createProjectRoleTestSuccess() {
        //Arrange
        Company company = new Company();
        //Act
        ProjectRole tester = company.getProjectRoleStore().createProjectRole("Tester");
        company.getProjectRoleStore().saveProjectRole(tester);
        //Assert
        assertEquals(tester, company.getProjectRoleStore().getProjectRole("Tester"));
    }

    @Test
    public void createProjectRoleTestNullFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            //Act
            company.getProjectRoleStore().createProjectRole(null);
        });
    }

    @Test
    public void createProjectRoleTestFail() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            //Act
            company.getProjectRoleStore().createProjectRole("");
        });
    }

    @Test
    public void getProjectRoleTestSuccess() {
        //Arrange
        Company company = new Company();
        //Act
        ProjectRole test = company.getProjectRoleStore().getProjectRole(1);
        //Assert
        assertEquals(test, company.getProjectRoleStore().getProjectRole("Project Manager"));
    }

    @Test
    public void getProjectRoleTestFail() {
        //Arrange
        Company company = new Company();
        //Act and Assert
        assertNull(company.getProjectRoleStore().getProjectRole(10));
    }

    @Test
    public void validateProjectRoleExistTestSuccess() {
        //Arrange
        Company company = new Company();
        //Act
        ProjectRole tester = new ProjectRole("Tester");
        // Assert
        assertTrue(company.getProjectRoleStore().saveProjectRole(tester));
    }

    @Test
    public void validateProjectRoleExistTestFail() {
        //Assert
        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Company company = new Company();
            //Act
            ProjectRole tester = new ProjectRole("Tester");
            company.getProjectRoleStore().saveProjectRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        });
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        ProjectRoleStore list1 = new ProjectRoleStore();
        list1.saveProjectRole(list1.createProjectRole("new"));
        ProjectRoleStore list2 = new ProjectRoleStore();
        list2.saveProjectRole(list1.createProjectRole("new"));
        ProjectRoleStore list3 = new ProjectRoleStore();
        list3.saveProjectRole(list3.createProjectRole("not new"));
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }
}