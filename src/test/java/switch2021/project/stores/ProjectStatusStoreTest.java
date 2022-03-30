package switch2021.project.stores;

import org.junit.jupiter.api.Test;
import switch2021.project.factory.ProjectStatusFactory;
import switch2021.project.valueObject.Description;
import switch2021.project.valueObject.ProjectStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectStatusStoreTest {
    @Test
    void populateDefaultStatusVerification() {
        //Arrange
        Description descriptionDouble1 = mock(Description.class);
        when(descriptionDouble1.getText()).thenReturn("Planned");
        Description descriptionDouble2 = mock(Description.class);
        when(descriptionDouble2.getText()).thenReturn("Inception");
        Description descriptionDouble3 = mock(Description.class);
        when(descriptionDouble3.getText()).thenReturn("Elaboration");
        Description descriptionDouble4 = mock(Description.class);
        when(descriptionDouble4.getText()).thenReturn("Construction");
        Description descriptionDouble5 = mock(Description.class);
        when(descriptionDouble5.getText()).thenReturn("Transition");
        Description descriptionDouble6 = mock(Description.class);
        when(descriptionDouble6.getText()).thenReturn("Warranty");
        Description descriptionDouble7 = mock(Description.class);
        when(descriptionDouble7.getText()).thenReturn("Closed");

        ProjectStatus projectStatusDouble1 = mock(ProjectStatus.class);
        when(projectStatusDouble1.getDescription()).thenReturn(descriptionDouble1);
        ProjectStatus projectStatusDouble2 = mock(ProjectStatus.class);
        when(projectStatusDouble2.getDescription()).thenReturn(descriptionDouble2);
        ProjectStatus projectStatusDouble3 = mock(ProjectStatus.class);
        when(projectStatusDouble3.getDescription()).thenReturn(descriptionDouble3);
        ProjectStatus projectStatusDouble4 = mock(ProjectStatus.class);
        when(projectStatusDouble4.getDescription()).thenReturn(descriptionDouble4);
        ProjectStatus projectStatusDouble5 = mock(ProjectStatus.class);
        when(projectStatusDouble5.getDescription()).thenReturn(descriptionDouble5);
        ProjectStatus projectStatusDouble6 = mock(ProjectStatus.class);
        when(projectStatusDouble6.getDescription()).thenReturn(descriptionDouble6);
        ProjectStatus projectStatusDouble7 = mock(ProjectStatus.class);
        when(projectStatusDouble7.getDescription()).thenReturn(descriptionDouble7);

        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        when(projectStatusFactoryDouble.createProjectStatus("Planned")).thenReturn(projectStatusDouble1);
        when(projectStatusFactoryDouble.createProjectStatus("Inception")).thenReturn(projectStatusDouble2);
        when(projectStatusFactoryDouble.createProjectStatus("Elaboration")).thenReturn(projectStatusDouble3);
        when(projectStatusFactoryDouble.createProjectStatus("Construction")).thenReturn(projectStatusDouble4);
        when(projectStatusFactoryDouble.createProjectStatus("Transition")).thenReturn(projectStatusDouble5);
        when(projectStatusFactoryDouble.createProjectStatus("Warranty")).thenReturn(projectStatusDouble6);
        when(projectStatusFactoryDouble.createProjectStatus("Closed")).thenReturn(projectStatusDouble7);

        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        //Act
        store.populateDefault();
        //Assert
        assertEquals(7, store.getProjectStatusList().size());
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
        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);

        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        when(projectStatusFactoryDouble.createProjectStatus("test")).thenReturn(projectStatusDouble);

        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        //Act
        boolean hasCreated = store.createAndSaveProjectStatus("test");
        //Assert
        assertTrue(hasCreated);
    }

    @Test
    void createAndSaveProjectStatusSuccess2() {
        //Arrange
        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        //Act
        boolean hasCreated = store.createAndSaveProjectStatus("test");
        //Assert
        assertTrue(hasCreated);
    }

    @Test
    void createAndSaveProjectStatusFailStatusAlreadyExists() {
        //Arrange
        Description descriptionDouble1 = mock(Description.class);
        when(descriptionDouble1.getText()).thenReturn("Planned");
        Description descriptionDouble2 = mock(Description.class);
        when(descriptionDouble2.getText()).thenReturn("Inception");
        Description descriptionDouble3 = mock(Description.class);
        when(descriptionDouble3.getText()).thenReturn("Elaboration");
        Description descriptionDouble4 = mock(Description.class);
        when(descriptionDouble4.getText()).thenReturn("Construction");
        Description descriptionDouble5 = mock(Description.class);
        when(descriptionDouble5.getText()).thenReturn("Transition");
        Description descriptionDouble6 = mock(Description.class);
        when(descriptionDouble6.getText()).thenReturn("Warranty");
        Description descriptionDouble7 = mock(Description.class);
        when(descriptionDouble7.getText()).thenReturn("Closed");

        ProjectStatus projectStatusDouble1 = mock(ProjectStatus.class);
        when(projectStatusDouble1.getDescription()).thenReturn(descriptionDouble1);
        ProjectStatus projectStatusDouble2 = mock(ProjectStatus.class);
        when(projectStatusDouble2.getDescription()).thenReturn(descriptionDouble2);
        ProjectStatus projectStatusDouble3 = mock(ProjectStatus.class);
        when(projectStatusDouble3.getDescription()).thenReturn(descriptionDouble3);
        ProjectStatus projectStatusDouble4 = mock(ProjectStatus.class);
        when(projectStatusDouble4.getDescription()).thenReturn(descriptionDouble4);
        ProjectStatus projectStatusDouble5 = mock(ProjectStatus.class);
        when(projectStatusDouble5.getDescription()).thenReturn(descriptionDouble5);
        ProjectStatus projectStatusDouble6 = mock(ProjectStatus.class);
        when(projectStatusDouble6.getDescription()).thenReturn(descriptionDouble6);
        ProjectStatus projectStatusDouble7 = mock(ProjectStatus.class);
        when(projectStatusDouble7.getDescription()).thenReturn(descriptionDouble7);

        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        when(projectStatusFactoryDouble.createProjectStatus("Planned")).thenReturn(projectStatusDouble1);
        when(projectStatusFactoryDouble.createProjectStatus("Inception")).thenReturn(projectStatusDouble2);
        when(projectStatusFactoryDouble.createProjectStatus("Elaboration")).thenReturn(projectStatusDouble3);
        when(projectStatusFactoryDouble.createProjectStatus("Construction")).thenReturn(projectStatusDouble4);
        when(projectStatusFactoryDouble.createProjectStatus("Transition")).thenReturn(projectStatusDouble5);
        when(projectStatusFactoryDouble.createProjectStatus("Warranty")).thenReturn(projectStatusDouble6);
        when(projectStatusFactoryDouble.createProjectStatus("Closed")).thenReturn(projectStatusDouble7);

        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        store.populateDefault();
        //Act
        boolean hasCreated = store.createAndSaveProjectStatus("Elaboration");
        //Assert
        assertFalse(hasCreated);
    }

    @Test
    void createAndSaveProjectStatusFailStatusAlreadyExists2() {
        //Arrange
        Description descriptionDouble = mock(Description.class);
        when(descriptionDouble.getText()).thenReturn("test");

        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        when(projectStatusDouble.getDescription()).thenReturn(descriptionDouble);

        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        when(projectStatusFactoryDouble.createProjectStatus("test")).thenReturn(projectStatusDouble);

        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        store.createAndSaveProjectStatus("test");
        //Act
        boolean hasCreated = store.createAndSaveProjectStatus("test");
        //Assert
        assertFalse(hasCreated);
    }

    @Test
    void createAndSaveProjectStatusFailStatusAlreadyExistsUpperCase() {
        //Arrange
        Description descriptionDouble = mock(Description.class);
        when(descriptionDouble.getText()).thenReturn("test");

        ProjectStatus projectStatusDouble = mock(ProjectStatus.class);
        when(projectStatusDouble.getDescription()).thenReturn(descriptionDouble);

        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        when(projectStatusFactoryDouble.createProjectStatus("test")).thenReturn(projectStatusDouble);

        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        store.createAndSaveProjectStatus("test");
        //Act
        boolean hasCreated = store.createAndSaveProjectStatus(" TEST ");
        //Assert
        assertFalse(hasCreated);
    }

    @Test
    void getProjectStatusEmptyList() {
        //Arrange
        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        //Act + Assert
        assertTrue(store.getProjectStatusList().isEmpty());
    }

    @Test
    void getProjectStatusListWithTwoProjectStatus() {
        //Arrange
        Description descriptionDouble1 = mock(Description.class);
        when(descriptionDouble1.getText()).thenReturn("Planned");
        Description descriptionDouble2 = mock(Description.class);
        when(descriptionDouble2.getText()).thenReturn("Inception");

        ProjectStatus projectStatusDouble1 = mock(ProjectStatus.class);
        when(projectStatusDouble1.getDescription()).thenReturn(descriptionDouble1);
        ProjectStatus projectStatusDouble2 = mock(ProjectStatus.class);
        when(projectStatusDouble2.getDescription()).thenReturn(descriptionDouble2);

        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        when(projectStatusFactoryDouble.createProjectStatus("Planned")).thenReturn(projectStatusDouble1);
        when(projectStatusFactoryDouble.createProjectStatus("Inception")).thenReturn(projectStatusDouble2);

        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        store.createAndSaveProjectStatus("Planned");
        store.createAndSaveProjectStatus("Inception");
        //Act
        List<ProjectStatus> projectStatusListTest = store.getProjectStatusList();
        //Assert
        assertEquals("Planned", projectStatusListTest.get(0).getDescription().getText());
        assertEquals("Inception", projectStatusListTest.get(1).getDescription().getText());
        assertEquals(2, store.getProjectStatusList().size());
    }

    @Test
    void getProjectStatusByDescriptionFailNotPresent() {
        //Arrange
        Description descriptionDouble1 = mock(Description.class);
        when(descriptionDouble1.getText()).thenReturn("Planned");
        Description descriptionDouble2 = mock(Description.class);
        when(descriptionDouble2.getText()).thenReturn("Inception");
        Description descriptionDouble3 = mock(Description.class);
        when(descriptionDouble3.getText()).thenReturn("Elaboration");
        Description descriptionDouble4 = mock(Description.class);
        when(descriptionDouble4.getText()).thenReturn("Construction");
        Description descriptionDouble5 = mock(Description.class);
        when(descriptionDouble5.getText()).thenReturn("Transition");
        Description descriptionDouble6 = mock(Description.class);
        when(descriptionDouble6.getText()).thenReturn("Warranty");
        Description descriptionDouble7 = mock(Description.class);
        when(descriptionDouble7.getText()).thenReturn("Closed");

        ProjectStatus projectStatusDouble1 = mock(ProjectStatus.class);
        when(projectStatusDouble1.getDescription()).thenReturn(descriptionDouble1);
        ProjectStatus projectStatusDouble2 = mock(ProjectStatus.class);
        when(projectStatusDouble2.getDescription()).thenReturn(descriptionDouble2);
        ProjectStatus projectStatusDouble3 = mock(ProjectStatus.class);
        when(projectStatusDouble3.getDescription()).thenReturn(descriptionDouble3);
        ProjectStatus projectStatusDouble4 = mock(ProjectStatus.class);
        when(projectStatusDouble4.getDescription()).thenReturn(descriptionDouble4);
        ProjectStatus projectStatusDouble5 = mock(ProjectStatus.class);
        when(projectStatusDouble5.getDescription()).thenReturn(descriptionDouble5);
        ProjectStatus projectStatusDouble6 = mock(ProjectStatus.class);
        when(projectStatusDouble6.getDescription()).thenReturn(descriptionDouble6);
        ProjectStatus projectStatusDouble7 = mock(ProjectStatus.class);
        when(projectStatusDouble7.getDescription()).thenReturn(descriptionDouble7);

        ProjectStatusFactory projectStatusFactoryDouble = mock(ProjectStatusFactory.class);
        when(projectStatusFactoryDouble.createProjectStatus("Planned")).thenReturn(projectStatusDouble1);
        when(projectStatusFactoryDouble.createProjectStatus("Inception")).thenReturn(projectStatusDouble2);
        when(projectStatusFactoryDouble.createProjectStatus("Elaboration")).thenReturn(projectStatusDouble3);
        when(projectStatusFactoryDouble.createProjectStatus("Construction")).thenReturn(projectStatusDouble4);
        when(projectStatusFactoryDouble.createProjectStatus("Transition")).thenReturn(projectStatusDouble5);
        when(projectStatusFactoryDouble.createProjectStatus("Warranty")).thenReturn(projectStatusDouble6);
        when(projectStatusFactoryDouble.createProjectStatus("Closed")).thenReturn(projectStatusDouble7);

        ProjectStatusStore store = new ProjectStatusStore(projectStatusFactoryDouble);
        store.populateDefault();
        //Act + Assert
        assertNull(store.getProjectStatusByDescription("new"));
    }
}