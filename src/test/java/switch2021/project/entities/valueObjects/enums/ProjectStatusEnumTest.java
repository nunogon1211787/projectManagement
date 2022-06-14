package switch2021.project.entities.valueObjects.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.ProjectStatusEnum;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


public class ProjectStatusEnumTest {

    @DisplayName("Test to compare two lists of project status values")
    @Test
    public void getProjectStatusDescription() {
        //Act
        List<String> statusList1 = ProjectStatusEnum.getProjectStatus();
        List<String> statusLis2 = List.of(new String[] {"PLANNED", "INCEPTION", "ELABORATION", "CONSTRUCTION",
                "TRANSITION", "WARRANTY", "CLOSED"});
        //Assert
        assertEquals(statusList1, statusLis2);
    }

    @DisplayName("Test to get a specific value - Planned")
    @Test
    public void value_Planned() {
        //Act
        ProjectStatusEnum[] status = ProjectStatusEnum.values();
        ProjectStatusEnum planned = status[0];
        //Assert
        assertEquals(planned, ProjectStatusEnum.PLANNED);
    }


    @DisplayName("Test to get a specific value - Inception")
    @Test
    public void value_Inception() {
        //Act
        ProjectStatusEnum[] status = ProjectStatusEnum.values();
        ProjectStatusEnum inception = status[1];
        //Assert
        assertEquals(inception, ProjectStatusEnum.INCEPTION);
    }


    @DisplayName("Test to get a specific value - Elaboration")
    @Test
    public void value_Elaboration() {
        //Act
        ProjectStatusEnum[] status = ProjectStatusEnum.values();
        ProjectStatusEnum elaboration = status[2];
        //Assert
        assertEquals(elaboration, ProjectStatusEnum.ELABORATION);
    }


    @DisplayName("Test to get a specific value - Construction")
    @Test
    public void value_Construction() {
        //Act
        ProjectStatusEnum[] status = ProjectStatusEnum.values();
        ProjectStatusEnum construction = status[3];
        //Assert
        assertEquals(construction, ProjectStatusEnum.CONSTRUCTION);
    }

    @DisplayName("Test to get a specific value - Transition")
    @Test
    public void value_Transition() {
        //Act
        ProjectStatusEnum[] status = ProjectStatusEnum.values();
        ProjectStatusEnum transition = status[4];
        //Assert
        assertEquals(transition, ProjectStatusEnum.TRANSITION);
    }


    @DisplayName("Test to get a specific value - Warranty")
    @Test
    public void value_Warranty() {
        //Act
        ProjectStatusEnum[] status = ProjectStatusEnum.values();
        ProjectStatusEnum warranty = status[5];
        //Assert
        assertEquals(warranty, ProjectStatusEnum.WARRANTY);
    }


    @DisplayName("Test to get a specific value - Closed")
    @Test
    public void value_Closed() {
        //Act
        ProjectStatusEnum[] status = ProjectStatusEnum.values();
        ProjectStatusEnum closed = status[6];
        //Assert
        assertEquals(closed, ProjectStatusEnum.CLOSED);
    }


    @DisplayName("Test to get a specific value - Fail")
    @Test
    public void value_fail(){
        //Act
        ProjectStatusEnum[] status = ProjectStatusEnum.values();
        ProjectStatusEnum closed = status[0];
        //Assert
        assertNotEquals(closed, ProjectStatusEnum.CLOSED);
    }


    @DisplayName("Test to valueOf - Planned")
    @Test
    public void valueOf_Planned() {
        //Act
        ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.valueOf("PLANNED");
        //Assert
        assertEquals(projectStatusEnum, ProjectStatusEnum.PLANNED);
    }

    @DisplayName("Test to valueOf - Inception")
    @Test
    public void valueOf_Inception() {
        //Act
        ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.valueOf("INCEPTION");
        //Assert
        assertEquals(projectStatusEnum, ProjectStatusEnum.INCEPTION);
    }

    @DisplayName("Test to valueOf - Elaboration")
    @Test
    public void valueOf_Elaboration() {
        //Act
        ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.valueOf("ELABORATION");
        //Assert
        assertEquals(projectStatusEnum, ProjectStatusEnum.ELABORATION);
    }

    @DisplayName("Test to valueOf - Construction")
    @Test
    public void valueOf_Construction() {
        //Act
        ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.valueOf("CONSTRUCTION");
        //Assert
        assertEquals(projectStatusEnum, ProjectStatusEnum.CONSTRUCTION);
    }

    @DisplayName("Test to valueOf - Transition")
    @Test
    public void valueOf_Transition() {
        //Act
        ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.valueOf("TRANSITION");
        //Assert
        assertEquals(projectStatusEnum, ProjectStatusEnum.TRANSITION);
    }


    @DisplayName("Test to valueOf - Warranty")
    @Test
    public void valueOf_Warranty() {
        //Act
        ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.valueOf("WARRANTY");
        //Assert
        assertEquals(projectStatusEnum, ProjectStatusEnum.WARRANTY);
    }


    @DisplayName("Test to valueOf - Closed")
    @Test
    public void valueOf_Closed() {
        //Act
        ProjectStatusEnum projectStatusEnum = ProjectStatusEnum.valueOf("CLOSED");
        //Assert
        assertEquals(projectStatusEnum, ProjectStatusEnum.CLOSED);
    }

}
