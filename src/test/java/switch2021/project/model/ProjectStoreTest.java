package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.controller.ProductBacklogController;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SystemUserStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectStoreTest {

    private Project proj1;
    private Project proj2;
    private Project proj3;
    private Project currentProject;

    @BeforeEach
    public void init() {
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        proj1 = company.getProjectStore().createProject("proj1Code", "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2021, 11, 30));

        proj2 = company.getProjectStore().createProject("proj2Code", "prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj2.setEndDate(LocalDate.of(2021, 11, 30));

        proj3 = company.getProjectStore().createProject("proj3Code", "prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj3.setEndDate(LocalDate.of(2021, 11, 30));

        currentProject = company.getProjectStore().createProject("proj4Code", "prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2021, 11, 15);
        Resource manuelbras = new Resource(user1, startDateMb, endDateMb, 100, .5);

        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource manuelmartins = new Resource(user2, startDateMm, endDateMm, 100, 1);

        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2021, 11, 15);
        Resource manueljose = new Resource(user3, startDateMj, endDateMj, 100, .5);

        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.of(2021, 11, 1);
        LocalDate endDateMo = LocalDate.of(2021, 11, 15);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);

        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2021, 11, 30);
        Resource manuelfernandes = new Resource(user5, startDateMf, endDateMf, 100, 1);

        SystemUser user6 = new SystemUser("manuelgoncalves", "manuelgoncalves@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMg = LocalDate.of(2021, 11, 16);
        LocalDate endDateMg = LocalDate.of(2021, 11, 30);
        Resource manuelgoncalves = new Resource(user6, startDateMg, endDateMg, 100, 1);

        proj1.getProjectTeam().addResourceToTeam(manuelbras);
        proj1.getProjectTeam().addResourceToTeam(manueljose);
        proj1.getProjectTeam().addResourceToTeam(manueloliveira);
        proj1.getProjectTeam().addResourceToTeam(manuelfernandes);
        proj2.getProjectTeam().addResourceToTeam(manuelbras);
        proj2.getProjectTeam().addResourceToTeam(manueloliveira);
        proj2.getProjectTeam().addResourceToTeam(manuelgoncalves);
        proj3.getProjectTeam().addResourceToTeam(manueljose);
        proj3.getProjectTeam().addResourceToTeam(manueloliveira);
        currentProject.getProjectTeam().addResourceToTeam(manuelmartins);
    }

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

        //company.getProjectStore().removeProject(project);
        //company.getProjectStore().removeProject(project2);

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

        //company.getProjectStore().removeProject(project);
        //company.getProjectStore().removeProject(project2);
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

        //company.getProjectStore().removeProject(project);
        //company.getProjectStore().removeProject(project2);
    }

    @Test
    public void getProjectByCodeSuccess() {
        // Arrange
        ProjectStore projectStore = company.getProjectStore();
        project.addResource(input);
        projectStore.addProject(this.project);
        // Act
        Project project1 = projectStore.getProjectByCode("123testcode");

        // Assert
        assertEquals(this.project, project1);

        projectStore.removeProject(this.project);
    }

    @Test
    public void getProjectByCodeFail() {
        // Arrange
        ProjectStore projectStore = company.getProjectStore();
        project.addResource(input);
        projectStore.addProject(this.project);
        // Act
        Project project = projectStore.getProjectByCode("123");

        // Assert
        assertNotEquals(this.project, project);

        //projectStore.removeProject(this.project);
    }

    @Test//US017
    public void getCurrentProjectListByUserEmailSucess() {
        //Arrange
        company.getProjectStore().addProject(this.proj1);
        company.getProjectStore().addProject(this.proj2);
        company.getProjectStore().addProject(this.proj3);
        company.getProjectStore().addProject(this.currentProject);
        // Act
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("manuelmartins@beaver.com");
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(1, sizeExpected);

        company.getProjectStore().removeProject(this.proj1);
        company.getProjectStore().removeProject(this.proj2);
        company.getProjectStore().removeProject(this.proj3);
        company.getProjectStore().removeProject(this.currentProject);
    }

    @Test//US017
    public void getCurrentProjectListByUserEmailFailResourceNotPresent() {
        //Arrange
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("manuelbras@beaver.com");
        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test//US017
    public void getCurrentProjectListByUserEmailFailResourceNotCurrent() {
        //Arrange
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("manueloliveira@beaver.com");
        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test//US015
    public void getProjectListEncapsulationSuccess() {
        ProjectStore projStore = company.getProjectStore();

        projStore.addProject(this.proj1);
        projStore.addProject(this.proj2);
        projStore.addProject(this.proj3);
        projStore.addProject(this.currentProject);

        List<Project> list = company.getProjectStore().getProjectList();
        list.remove(0);

        assertEquals(4, projStore.getProjectList().size());

        projStore.removeProject(this.proj1);
        projStore.removeProject(this.proj2);
        projStore.removeProject(this.proj3);
        projStore.removeProject(this.currentProject);
    }

    @Test//US015
    public void getProjectListSuccessEmpty() {
        //Arrange
        List<Project> projectList = company.getProjectStore().getProjectList();
        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test//US015
    public void getProjectListSuccessWith2Projects() {
        //Arrange
        ProjectStore projStore = company.getProjectStore();

        projStore.addProject(this.proj1);
        projStore.addProject(this.proj2);

        List<Project> projectList = company.getProjectStore().getProjectList();
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(2, sizeExpected);

        projStore.removeProject(this.proj1);
        projStore.removeProject(this.proj2);
    }
}
