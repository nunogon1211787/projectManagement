package switch2021.project.entities.valueObjects.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.entities.valueObjects.vos.enums.ProjectRoleReeng;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectRoleReengTest {

    @DisplayName("Test to compare two lists of project roles values")
    @Test
    public void getProjectRoleReengDescription() {
        //Act
        List<String> statusList1 = ProjectRoleReeng.getProjectRole();
        List<String> statusList2 = List.of(new String[]{"ProjectManager", "ProductOwner", "ScrumMaster", "TeamMember"});
        //Assert
        assertEquals(statusList1, statusList2);
    }

    @DisplayName("Test to get a specific value - Project Manager")
    @Test
    public void value_ProjectManager() {
        //Act
        ProjectRoleReeng[] status = ProjectRoleReeng.values();
        ProjectRoleReeng projectManager = status[0];
        //Assert
        assertEquals(projectManager, ProjectRoleReeng.ProjectManager);

    }


    @DisplayName("Test to get a specific value - Product Owner")
    @Test
    public void value_ProductOwner() {
        //Act
        ProjectRoleReeng[] status = ProjectRoleReeng.values();
        ProjectRoleReeng productOwner = status[1];
        //Assert
        assertEquals(productOwner, ProjectRoleReeng.ProductOwner);

    }


    @DisplayName("Test to get a specific value - Scrum Master")
    @Test
    public void value_ScrumMaster() {
        //Act
        ProjectRoleReeng[] status = ProjectRoleReeng.values();
        ProjectRoleReeng scrumMaster = status[2];
        //Assert
        assertEquals(scrumMaster, ProjectRoleReeng.ScrumMaster);

    }


    @DisplayName("Test to get a specific value - Team Member")
    @Test
    public void value_TeamMember() {
        //Act
        ProjectRoleReeng[] status = ProjectRoleReeng.values();
        ProjectRoleReeng teamMember = status[3];
        //Assert
        assertEquals(teamMember, ProjectRoleReeng.TeamMember);

    }

    @DisplayName("Test to get a specific value - Fail")
    @Test
    public void value_Fail() {
        //Act
        ProjectRoleReeng[] status = ProjectRoleReeng.values();
        ProjectRoleReeng teamMember = status[3];
        //Assert
        assertNotEquals(teamMember, ProjectRoleReeng.ScrumMaster);

    }


    @DisplayName("Test to valueOf - Project Manager")
    @Test
    public void valueOf_ProjectManager() {
        //Act
        ProjectRoleReeng projectRoleReeng = ProjectRoleReeng.valueOf("ProjectManager");
        //Assert
        assertEquals(projectRoleReeng, ProjectRoleReeng.ProjectManager);
    }


    @DisplayName("Test to valueOf - Product Owner")
    @Test
    public void valueOf_ProductOwner() {
        //Act
        ProjectRoleReeng projectRoleReeng = ProjectRoleReeng.valueOf("ProductOwner");
        //Assert
        assertEquals(projectRoleReeng, ProjectRoleReeng.ProductOwner);
    }


    @DisplayName("Test to valueOf - Scrum Master")
    @Test
    public void valueOf_ScrumMaster() {
        //Act
        ProjectRoleReeng projectRoleReeng = ProjectRoleReeng.valueOf("ScrumMaster");
        //Assert
        assertEquals(projectRoleReeng, ProjectRoleReeng.ScrumMaster);
    }



    @DisplayName("Test to valueOf - Team Member")
    @Test
    public void valueOf_TeamMember() {
        //Act
        ProjectRoleReeng projectRoleReeng = ProjectRoleReeng.valueOf("TeamMember");
        //Assert
        assertEquals(projectRoleReeng, ProjectRoleReeng.TeamMember);
    }


}
