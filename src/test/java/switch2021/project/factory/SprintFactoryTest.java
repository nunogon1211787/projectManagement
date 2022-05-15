package switch2021.project.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.dto.NewSprintDTO;
import switch2021.project.factoryInterface.IDescriptionFactory;
import switch2021.project.factoryInterface.IProjectIDFactory;
import switch2021.project.factoryInterface.ISprintIDFactory;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.valueObject.Description;
import switch2021.project.model.valueObject.ProjectID;
import switch2021.project.model.valueObject.SprintID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SprintFactoryTest {

    @Mock
    private IDescriptionFactory iDescriptionFactory;
    @Mock
    private Description name;
    @Mock
    private IProjectIDFactory iProjectIDFactory;
    @Mock
    private ProjectID projectID;
    @Mock
    private ISprintIDFactory iSprintIDFactory;
    @Mock
    private SprintID sprintID;
    @Mock
    private NewSprintDTO newSprintDTO;

    @InjectMocks
    private SprintFactory sprintFactory;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test to create a sprint, with success")
    public void createSprint_Success(){
        //Arrange

        when(iDescriptionFactory.createDescription(newSprintDTO.name)).thenReturn(name);
        when(iProjectIDFactory.create(newSprintDTO.projectID)).thenReturn(projectID);
        when(iSprintIDFactory.create(newSprintDTO.name, newSprintDTO.projectID)).thenReturn(sprintID);

        //Act
        Sprint sprint = sprintFactory.createSprint(newSprintDTO);

        //Assert
        assertNotNull(sprint);
    }
}
