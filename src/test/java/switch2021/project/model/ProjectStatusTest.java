package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.stores.ProjectStatusStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProjectStatusTest {
    @Test
    public void createProjectStatusSuccess() {
        //Arrange
        String descriptionExpected = "new Project Status";
        //Act
        ProjectStatus projStatus = new ProjectStatus("new Project Status");
        //Assert
        assertEquals(descriptionExpected, projStatus.getDescription());
    }

    @Test
    public void createProjectStatusFailDescriptionIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Act
            new ProjectStatus("");
        });
    }

    @Test
    public void createProjectStatusFailDescriptionLenghtEquals2() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Act
            new ProjectStatus("to");
        });
    }

    @Test
    public void createProjectStatusFailDescriptionLenghtEquals3() {
        //Arrange
        String descriptionExpected = "new";
        //Act
        ProjectStatus projStatus = new ProjectStatus("new");
        //Assert
        assertEquals(descriptionExpected, projStatus.getDescription());
    }

    @Test
    public void setProjectStatusDescription() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore projectStatusStore = company.getProjectStatusStore();
        ProjectStatus projStatus = projectStatusStore.getProjectStatusByDescription("Planned");
        //Act
        projStatus.setDescription("Elaboration");
        //Assert
        assertEquals("Elaboration", projStatus.getDescription());
    }
}
