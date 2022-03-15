package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectStatusStoreTest {

    @Test
    void populateDefaultStatusVerification() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Assert
        assertEquals(7,store.getProjectStatusList().size());
        assertEquals("Planned", store.getProjectStatusByDescription("Planned").getDescription().getText());
        assertEquals("Inception", store.getProjectStatusByDescription("Inception").getDescription().getText());
        assertEquals("Elaboration", store.getProjectStatusByDescription("Elaboration").getDescription().getText());
        assertEquals("Construction", store.getProjectStatusByDescription("Construction").getDescription().getText());
        assertEquals("Transition", store.getProjectStatusByDescription("Transition").getDescription().getText());
        assertEquals("Warranty", store.getProjectStatusByDescription("Warranty").getDescription().getText());
        assertEquals("Closed", store.getProjectStatusByDescription("Closed").getDescription().getText());
    }

    @Test
    void createProjectStatusSuccess() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Act
        ProjectStatus newStatus = store.createProjectStatus("test");
        //Assert
        assertEquals(7,store.getProjectStatusList().size());
        assertEquals("test", newStatus.getDescription().getText());
    }

    @Test
    void addProjectStatusSuccess() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        ProjectStatus newStatus = store.createProjectStatus("test");
        //Act
        boolean result = store.add(newStatus);
        //Assert
        assertTrue(result);
        assertEquals(8,store.getProjectStatusList().size());
        assertEquals("test",store.getProjectStatusByDescription("test").getDescription().getText());
    }

    @Test
    void addProjectStatusFail() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        ProjectStatus newStatus = store.createProjectStatus("Elaboration");
        //Act
        boolean result = store.add(newStatus);
        //Assert
        assertEquals(7,store.getProjectStatusList().size());
        assertFalse(result);
    }

    @Test
    void getProjectStatusList() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();

        List<ProjectStatus> status = new ArrayList<>();
        status.add(new ProjectStatus("Planned"));
        status.add(new ProjectStatus("Inception"));
        status.add(new ProjectStatus("Elaboration"));
        status.add(new ProjectStatus("Construction"));
        status.add(new ProjectStatus("Transition"));
        status.add(new ProjectStatus("Warranty"));
        status.add(new ProjectStatus("Closed"));
        //Assert
        assertEquals(status, store.getProjectStatusList());
    }

    @Test
    void getProjectStatusByDescription() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();

        ProjectStatus status = new ProjectStatus("Planned");
        //Assert
        assertEquals(status, store.getProjectStatusByDescription("Planned"));
    }

    @Test
    void getProjectStatusByDescription_null() {
        ProjectStatusStore store = new ProjectStatusStore();
        store.populateDefault();

        String status ="null";

        assertNull(store.getProjectStatusByDescription(status));
    }

    @Test
    void overrideEquals() {
        ProjectStatus status1 = new ProjectStatus("teste");
        ProjectStatus status2 = new ProjectStatus("teste");


        assertEquals(status1,status2);
    }

    @Test
    void overrideNotEquals() {
        ProjectStatus status1 = new ProjectStatus("teste");
        ProjectStatus status2 = new ProjectStatus("teste2");

        assertNotEquals(status1,status2);
        assertEquals(status1.getClass(),status2.getClass());
        assertNotEquals( null,status1);
    }

    @Test
    void hashCodeTest() {
        ProjectStatus status1 = new ProjectStatus("teste");
        ProjectStatus status2 = new ProjectStatus("teste2");

        assertNotEquals(status1.hashCode(),status2.hashCode());
    }

    @Test
    void overrideTest() {
        ProjectStatus status1 = new ProjectStatus("teste");
        ProjectStatus status2 = new ProjectStatus("teste");
        ProjectStatus status3 = null;
        TaskType typo = new TaskType("test");

        assertEquals(status1,status2);
        assertNotEquals(status1,status3);
        assertNotEquals(status1,typo);
    }
}