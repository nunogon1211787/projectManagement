package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.model.ProjectStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProjectStatusStoreTest {

    @Test
    void populateDefault() {
        ProjectStatusStore store = new ProjectStatusStore();
        store.populateDefault();

        assertEquals("Planned", store.getprojectStatusList().get(0).getDescription());
        assertEquals("Inception", store.getprojectStatusList().get(1).getDescription());
        assertEquals("Elaboration", store.getprojectStatusList().get(2).getDescription());
        assertEquals("Construction", store.getprojectStatusList().get(3).getDescription());
        assertEquals("Transition", store.getprojectStatusList().get(4).getDescription());
        assertEquals("Warranty", store.getprojectStatusList().get(5).getDescription());
        assertEquals("Closed", store.getprojectStatusList().get(6).getDescription());
    }

    @Test
    void createProjectStatus() {
        ProjectStatusStore store = new ProjectStatusStore();
        ProjectStatus test = store.createProjectStatus("test");

        assertEquals("test", test.getDescription());
    }

    @Test
    void add() {
        ProjectStatusStore store = new ProjectStatusStore();
        store.populateDefault();
        ProjectStatus test = store.createProjectStatus("test");
        store.add(test);

        assertEquals("test",store.getprojectStatusList().get(7).getDescription());
    }

    @Test
    void getprojectStatusList() {
        ProjectStatusStore store = new ProjectStatusStore();
        store.populateDefault();

        List<ProjectStatus> status = new ArrayList<>();
        status.add(new ProjectStatus("Planned"));
        status.add(new ProjectStatus("Inception"));
        status.add(new ProjectStatus("Elaboration"));
        status.add(new ProjectStatus("Construction"));
        status.add(new ProjectStatus("Transition"));
        status.add(new ProjectStatus("Warranty"));
        status.add(new ProjectStatus("Closed"));

        assertEquals(status, store.getprojectStatusList());
    }

    @Test
    void getProjectStatusByDescription() {
        ProjectStatusStore store = new ProjectStatusStore();
        store.populateDefault();

        ProjectStatus status = new ProjectStatus("Planned");

        assertEquals(status, store.getProjectStatusByDescription("Planned"));
    }

    @Test
    void getProjectStatusByDescription_null() {
        ProjectStatusStore store = new ProjectStatusStore();
        store.populateDefault();

        String status ="null";

        assertNull(store.getProjectStatusByDescription(status));
    }
}