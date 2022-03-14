package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.Immutables.Description;
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
        assertEquals(descriptionExpected, projStatus.getDescription().getDescriptionF());
    }

    @Test
    public void createProjectStatusFailDescriptionIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Act
            new ProjectStatus("");
        });
    }

    @Test
    public void createProjectStatusFailDescriptionLengthEquals3() {
        //Arrange
        String descriptionExpected = "new";
        //Act
        ProjectStatus projStatus = new ProjectStatus("new");
        //Assert
        assertEquals(descriptionExpected, projStatus.getDescription().getDescriptionF());
    }

    @Test
    public void setProjectStatusDescription() {
        //Arrange
        Company company = new Company();
        ProjectStatusStore projectStatusStore = company.getProjectStatusStore();
        ProjectStatus projStatus = projectStatusStore.getProjectStatusByDescription("Planned");

        Description newStatusDescription = projectStatusStore.getProjectStatusByDescription("Elaboration").getDescription();
        //Act
        projStatus.setDescription(newStatusDescription);
        //Assert
        assertEquals("Elaboration", projStatus.getDescription().getDescriptionF());
    }
}
