package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.model.valueObject.SprintID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SprintIDFactoryTest {

    @InjectMocks
    SprintIDFactory sprintIDFactory;

    @Mock
    private ProjectIDFactory projectIDFactory;

    @Mock
    private DescriptionFactory descriptionFactory;

    @Mock
    private ProjectID projectID;

    @Mock
    private Description description;

    @Mock
    private SprintID sprintID;

    @Test
    @DisplayName("Test to create a SprintID - success")
    public void createSprintID_Success(){
        //Arrange
        String project = "Project_2022_1";
        projectID = new ProjectID(project);
        when(projectIDFactory.create(project)).thenReturn(projectID);
        String des = "Sprint Name";
        description = new Description(des);
        when(descriptionFactory.createDescription(des)).thenReturn(description);
        //Act
        sprintID = sprintIDFactory.create(project, des);
        String x = "Project_2022_Project_2022_1_Sprint Name";
        //Assert
        assertEquals(x, sprintID.toString());
    }

}
