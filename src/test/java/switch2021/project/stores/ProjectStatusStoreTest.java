package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.factory.ProjectStatusFactory;
import switch2021.project.model.Company;
import switch2021.project.model.ProjectStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void createAndSaveProjectStatusSuccess() {
        //Arrange
        ProjectStatusStore store = new ProjectStatusStore();
        String description = "test";
        //Act
        boolean hasCreated = store.createAndSaveProjectStatus(description);
        //Assert
        assertTrue(hasCreated);
    }

    @Test
    void createAndSaveProjectStatusSuccessWithMockito() {
        //Arrange
        String description = "test";

        ProjectStatus projectStatusDuplo = mock(ProjectStatus.class);

        ProjectStatusFactory projectStatusFactoryDuplo = mock(ProjectStatusFactory.class);
        when(projectStatusFactoryDuplo.createProjectStatus(description)).thenReturn(projectStatusDuplo);

        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDuplo);
        //Act
        boolean hasCreated = store.createAndSaveProjectStatusWithFactory(description);
        //Assert
        assertTrue(hasCreated);
    }

    @Test
    void createAndSaveProjectStatusFailStatusAlreadyExists2() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        store.createAndSaveProjectStatus("test");
        //Act
        assertThrows(IllegalArgumentException.class, () ->
                store.createAndSaveProjectStatus("test")
        );
    }

    @Test
    void createAndSaveProjectStatusFailStatusAlreadyExists() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Act
        assertThrows(IllegalArgumentException.class, () ->
                store.createAndSaveProjectStatus("Elaboration")
        );
    }

    @Test
    void createAndSaveProjectStatusFailStatusAlreadyExistsUpperCase() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Act
        assertThrows(IllegalArgumentException.class, () ->
                store.createAndSaveProjectStatus(" ELABORATION ")
        );
    }

    @Test
    void createAndSaveProjectStatusFailStatusAlreadyExistsLowerCase() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Act
        assertThrows(IllegalArgumentException.class, () ->
                store.createAndSaveProjectStatus(" elaboration ")
        );
    }

    @Test
    void createAndSaveProjectStatusFailEmptyStatus() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Act
        assertThrows(IllegalArgumentException.class, () ->
                store.createAndSaveProjectStatus("")
        );
    }

    @Test
    void getProjectStatusList() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Act
        List<ProjectStatus> statusListCopy = new ArrayList<>(store.getProjectStatusList());
        //Assert
        assertEquals(statusListCopy.size(), store.getProjectStatusList().size());
        assertEquals(statusListCopy, store.getProjectStatusList());
    }

    @Test
    void getProjectStatusByDescriptionSuccess() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Act
        ProjectStatus status = store.getProjectStatusByDescription("Planned");
        //Assert
        assertEquals("Planned", status.getDescription().getText());
    }

    @Test
    void getProjectStatusByDescriptionFail() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore store = company.getProjectStatusStore();
        //Assert
        assertNull(store.getProjectStatusByDescription("new"));
    }

    @Test
    void getProjectStatusByDescription_null() {
        ProjectStatusStore store = new ProjectStatusStore();
        store.populateDefault();

        String status = "null";

        assertNull(store.getProjectStatusByDescription(status));
    }
}