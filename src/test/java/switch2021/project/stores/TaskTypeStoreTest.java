package switch2021.project.stores;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.factory.TaskTypeFactory;
import switch2021.project.valueObject.Description;
import switch2021.project.model.TaskType.TaskType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TaskTypeStoreTest {

    @Test
    @DisplayName("Test to verify the populate method - Test size, with all descriptions.")
    void populateDefaultTestSize_Success() {
        //Arrange
        TaskTypeFactory taskTypeFactory = mock(TaskTypeFactory.class);
        TaskTypeStore tasktypestore = new TaskTypeStore(taskTypeFactory);
        TaskType tasktype = mock(TaskType.class);
        //Act
        when(taskTypeFactory.createTaskType("Meeting")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Documentation")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Design")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Implementation")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Testing")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Deployment")).thenReturn(tasktype);
        tasktypestore.populateDefault();
        //Assert
        assertEquals(6, tasktypestore.getTaskTypeList().size());
    }

    @Test
    @DisplayName("Test to verify the populate method - Test size, with all descriptions.")
    void populateDefaultTestSize_Fail() {
        //Arrange
        TaskTypeFactory taskTypeFactory = mock(TaskTypeFactory.class);
        TaskTypeStore tasktypestore = new TaskTypeStore(taskTypeFactory);
        TaskType tasktype = mock(TaskType.class);
        when(taskTypeFactory.createTaskType("Meeting")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Documentation")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Design")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Implementation")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Testing")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Deployment")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("New Task Type")).thenReturn(tasktype);
        //Act
        tasktypestore.populateDefault();
        //Assert
        assertNotEquals(7, tasktypestore.getTaskTypeList().size());
    }

    @Test
    @DisplayName("Test to verify the creation method, with success.")
    void createTaskTypeSuccess() {
        //Arrange
        TaskTypeStore store = mock(TaskTypeStore.class);
        when(store.createAndAddTaskType("New Task Type")).thenReturn(true);
        //Act
        store.populateDefault();
        boolean y = store.createAndAddTaskType("New Task Type");
        //Assert
        assertTrue(y);
    }

    @Test
    @DisplayName("Test to verify the creation method, with success.")
    void createTaskTypeSuccess_1() {
        //Arrange
        TaskTypeStore store = mock(TaskTypeStore.class);
        when(store.createAndAddTaskType("New Task Type")).thenReturn(true);
        //Act
        store.populateDefault();
        boolean y = store.createAndAddTaskType("New Task Type");
        //Assert
        assertTrue(y);
    }

    @Test
    @DisplayName("Test to verify the creation method, with failure (different description).")
    void createTaskTypeFail_1() {
        //Arrange
        TaskTypeStore store = mock(TaskTypeStore.class);
        when(store.createAndAddTaskType("New Task Type")).thenReturn(true);
        //Act
        store.populateDefault();
        boolean y = store.createAndAddTaskType("Old Task Type");
        //Assert
        assertFalse(y);
    }

    @Test
    @DisplayName("Test to verify the creation method, with failure (description already exists).")
    void createTaskTypeFail_2() {
        //Arrange
        TaskTypeStore store = mock(TaskTypeStore.class);
        when(store.createAndAddTaskType("Meeting")).thenReturn(false);
        //Act
        store.populateDefault();
        boolean y = store.createAndAddTaskType("Meeting");
        //Assert
        assertFalse(y);
    }

    @Test
    @DisplayName("Test to verify the creation method, with failure (null description).")
    void createTaskTypeFail_3() {
        //Arrange
        TaskTypeStore store = mock(TaskTypeStore.class);
        when(store.createAndAddTaskType(null)).thenReturn(false);
        //Act
        store.populateDefault();
        boolean y = store.createAndAddTaskType(null);
        //Assert
        assertFalse(y);
    }

    @Test
    @DisplayName("Test to verify if the description is returned, with success.")
    void getTypeByDescriptionSuccess() {
        //Arrange
        TaskTypeFactory taskTypeFactory = mock(TaskTypeFactory.class);
        TaskTypeStore store = new TaskTypeStore(taskTypeFactory);
        store.getTaskTypeList().add(new TaskType("Meeting"));
        TaskType tasktype = mock(TaskType.class);
        Description description = mock(Description.class);
        when(tasktype.hasDescription("Meeting")).thenReturn(true);
        when(tasktype.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Meeting");
        //Act
        String x = store.getTypeByDescription("Meeting").getDescription().getText();
        //Assert
        assertEquals("Meeting", x);
    }

    @Test
    @DisplayName("Test  to verify if the description is returned, with failure.")
    void getTypeByDescriptionFail() {
        //Arrange
        TaskTypeFactory taskTypeFactory = mock(TaskTypeFactory.class);
        TaskTypeStore store = new TaskTypeStore(taskTypeFactory);
        store.getTaskTypeList().add(new TaskType("Meeting"));
        store.getTaskTypeList().add(new TaskType("Documentation"));
        TaskType tasktype = mock(TaskType.class);
        Description description = mock(Description.class);
        when(tasktype.hasDescription("Meeting")).thenReturn(true);
        when(tasktype.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("Meeting");
        //Act
        String x = store.getTypeByDescription("Documentation").getDescription().getText();
        //Assert
        assertNotEquals("Meeting", x);
    }

    @Test
    @DisplayName("Test to verify if the list of descriptions is empty.")
    void getTypeByDescriptionListEmpty() {
        //Arrange
        TaskTypeFactory taskTypeFactory = mock(TaskTypeFactory.class);
        TaskTypeStore store = new TaskTypeStore(taskTypeFactory);
        //Act and Arrange
        assertTrue(store.getTaskTypeList().isEmpty());
    }

    @Test
    @DisplayName("Test to verify if the list of descriptions is equal to 0.")
    void getTaskTypeDescriptionList() {
        //Arrange
        TaskTypeFactory taskTypeFactory = mock(TaskTypeFactory.class);
        TaskTypeStore store = new TaskTypeStore(taskTypeFactory);
        //Act and Arrange
        assertEquals(0, store.getTaskTypesDescription().size());
    }


    @Test
    @DisplayName("Test to get the list of task type descriptions")
    void getTaskStatusNamesSizeTest() {
        //Arrange
        TaskTypeFactory taskTypeFactory = mock(TaskTypeFactory.class);
        TaskTypeStore store = new TaskTypeStore(taskTypeFactory);
        TaskType tasktype = mock(TaskType.class);
        Description description = mock(Description.class);
        //Act
        when(taskTypeFactory.createTaskType("Meeting")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Documentation")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Design")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Implementation")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Testing")).thenReturn(tasktype);
        when(taskTypeFactory.createTaskType("Deployment")).thenReturn(tasktype);
        store.populateDefault();
        when(tasktype.getDescription()).thenReturn(description);
        when(description.getText()).thenReturn("");
        List<String> testList = store.getTaskTypesDescription();
        //Assert
        assertEquals(6, testList.size());
    }

    @Test
    void constructorFactory() {
        //Arrange
        TaskTypeFactory taskTypeFactory = new TaskTypeFactory();
        //Act
        TaskTypeStore taskTypeStore = new TaskTypeStore(taskTypeFactory);
        //Assert
        assertEquals(taskTypeFactory,taskTypeStore.getTaskTypeFactoryInterface());
    }

}