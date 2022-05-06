package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class SprintFactoryTest {

    @InjectMocks
    SprintFactory sprintFactory;

    @Mock
    Sprint sprint;

    @Mock
    SprintIDFactory sprintIDFactory;

    @Mock
    SprintID sprintID;

    @Mock
    ProjectIDFactory projectIDFactory;

    @Mock
    ProjectID projectID;

    @Mock
    DescriptionFactory descriptionFactory;

    @Mock
    Description description;


//    @Test
//    @DisplayName("Test to create a sprint, with success")
//    public void createSprint_Success(){
//        //Arrange
//        String project = "Project_2022_1";
//        String des = "Sprint Name";
//        //Act
//        projectID = projectIDFactory.create(project);
//        description = descriptionFactory.create(des);
//        sprintID = sprintIDFactory.create(project, des);
//        NewSprintDTO newSprintDTO = new NewSprintDTO();
//        sprint = sprintFactory.createSprint(newSprintDTO);
//        SprintID abc = new SprintID("Project_2022_1_Sprint that we need to test");
//        Sprint sprintX = new Sprint (abc);
//        //Assert
//        assertFalse(sprintX.sameIdentityAs(sprint));
//    }

}
