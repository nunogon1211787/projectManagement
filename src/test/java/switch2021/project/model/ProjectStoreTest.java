package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.controller.ProductBacklogController;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SystemUserStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectStoreTest {

    Company company = new Company();
    Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
    Customer customer = company.getCustomerStore().getCustomerByName("Teste");
    BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
    Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);
    Project project2 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
            typo, sector, LocalDate.now(), 7, 5000);

    UserProfile userProfile = new UserProfile("zzz");
    SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
    LocalDate startDate = LocalDate.of(2021, 12, 31);
    LocalDate endDate = LocalDate.of(2022, 1, 5);
    Resource input = new Resource(newUser, startDate, endDate, 100, .5);


    @Test
    public void getProjectListByUserEmail() {
        //Arrange
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        List<Project> projectList = company.getProjectStore().getProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());

    }

    @Test

    public void getProjectListByUserEmailIsBlank() {
        //Arrange
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProjectStore projectStore = new ProjectStore();
            List<Project> projectList = projectStore.getProjectListByUserEmail("");
        });
        // Assert
        assertEquals("Email cannot be blank", exception.getMessage());

    }

    @Test

    public void getAllProjectListByUserEmailDontExist() {
        //Arrange
        company.getProjectStore().addProject(project);
        company.getProjectStore().addProject(project2);
        project.addResource(input);
        project2.addResource(input);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProjectStore projectStore = new ProjectStore();
            List<Project> projectList = projectStore.getProjectListByUserEmail("dani@ipp.pt");
        });
        // Assert
        assertEquals("Email don't exist in system", exception.getMessage());

    }

    @Test
    public void getProjectByCodeSuccess() {
        // Arrange
        ProjectStore projectStore = new ProjectStore();
        project.addResource(input);
        projectStore.addProject(this.project);
        // Act
        Project project = projectStore.getProjectByCode("123testcode");

        // Assert
        assertEquals(this.project, project);
    }

    @Test
    public void getProjectByCodeFail() {
        // Arrange
        ProjectStore projectStore = new ProjectStore();
        project.addResource(input);
        projectStore.addProject(this.project);
        // Act
        Project project = projectStore.getProjectByCode("123");

        // Assert
        assertNotEquals(this.project, project);
    }

    @Test//US017
    public void getCurrentProjectListByUserEmailSucess() {
        //Arrange
        ProjectTeamTest test = new ProjectTeamTest();
        company.getProjectStore().addProject(test.getProj1());
        company.getProjectStore().addProject(test.getProj2());
        company.getProjectStore().addProject(test.getProj3());
        company.getProjectStore().addProject(test.getCurrentProject());
        // Act
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("manuelmartins@beaver.com");
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(1, sizeExpected);
    }

    @Test//US017
    public void getCurrentProjectListByUserEmailFailResourceNotPresent() {
        //Arrange
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("manueloliveira@beaver.com");
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(0, sizeExpected);
    }

    @Test//US017
    public void getCurrentProjectListByUserEmailFailResourceNotCurrent() {
        //Arrange
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("manueloliveira@beaver.com");
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(0, sizeExpected);
    }

    @Test//US015
    public void getProjectListEncapsulationSuccess() {
        ProjectStore projStore = company.getProjectStore();
        ProjectTeamTest test = new ProjectTeamTest();

        projStore.addProject(test.getProj1());
        projStore.addProject(test.getProj2());
        projStore.addProject(test.getProj3());
        projStore.addProject(test.getCurrentProject());

        List<Project> list = company.getProjectStore().getProjectList();
        list.remove(0);

        assertEquals(4, projStore.getProjectList().size());
    }

    @Test//US015
    public void getProjectListSuccessEmpty() {
        //Arrange
        List<Project> projectList = company.getProjectStore().getProjectList();
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(0, sizeExpected);
    }

    @Test//US015
    public void getProjectListSuccessWith2Projects() {
        //Arrange
        ProjectStore projStore = company.getProjectStore();
        ProjectTeamTest test = new ProjectTeamTest();

        projStore.addProject(test.getProj1());
        projStore.addProject(test.getProj2());

        List<Project> projectList = company.getProjectStore().getProjectList();
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(2, sizeExpected);
    }
}
