package switch2021.project.stores;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectRoleStoreTest {

    @Test
    void createProjectRole() {
//        ProjectRoleStore projectRoleStore = new ProjectRoleStore();
//        ProjectRole projRole = projectRoleStore.createProjectRole("Motorista");

    }

    @Test
    void addProjectRole() {
    }

    @Test
    void getProjectRolesList() {
    }

    @Test
    void getProjectRole() {
    }

    @Test
    void testGetProjectRole() {
    }

    @Test
    public void overrideAndHashCodeTest() {
        //Arrange
        ProjectRoleStore list1 = new ProjectRoleStore();
        list1.addProjectRole(list1.createProjectRole("new"));
        ProjectRoleStore list2 = new ProjectRoleStore();
        list2.addProjectRole(list1.createProjectRole("new"));
        ProjectRoleStore list3 = new ProjectRoleStore();
        list3.addProjectRole(list3.createProjectRole("not new"));
        //Assert
        assertNotSame(list1, list2);
        assertEquals(list1, list2);
        assertEquals(list1.hashCode(), list2.hashCode());
        assertNotEquals(list1, list3);
        assertNotEquals(list1.hashCode(), list3.hashCode());
    }
}