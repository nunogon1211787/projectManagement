package switch2021.project.factory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import switch2021.project.model.valueObject.ProjectID;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class ProjectIDFactoryTest {

    @InjectMocks
    ProjectIDFactory projectIDFactory;

    @Mock
    ProjectID projectID;

    @Test
    @DisplayName("Test to create a projectID, success")
    public void createProjectID_Success(){
        //Arrange
        String x = "Project_2022_1";
        //Act
        projectID = projectIDFactory.create(x);
        //Assert
        assertEquals(x, projectID.getCode());
    }

}
