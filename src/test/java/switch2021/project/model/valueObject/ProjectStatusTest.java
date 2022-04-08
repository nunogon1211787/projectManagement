package switch2021.project.model.valueObject;

import org.junit.jupiter.api.Test;
import switch2021.project.model.Project.ProjectStatus;

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
        assertEquals(descriptionExpected, projStatus.getDescription().getText());
    }

    @Test
    public void createProjectStatusFailDescriptionIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            //Act
            new ProjectStatus("");
        });
    }

    @Test
    public void createProjectStatusSuccessDescriptionLengthEquals1() {
        //Arrange
        String descriptionExpected = "n";
        //Act
        ProjectStatus projStatus = new ProjectStatus("n");
        //Assert
        assertEquals(descriptionExpected, projStatus.getDescription().getText());
    }
}
