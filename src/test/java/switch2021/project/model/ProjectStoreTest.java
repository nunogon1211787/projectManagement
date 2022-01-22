package switch2021.project.model;

import org.junit.jupiter.api.Test;
import switch2021.project.controller.ProductBacklogController;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
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

    @Test
    public void getCurrentProjectListByUserEmailSucess() {
        /*//Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        Project project1 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.of(2021, 12, 1), 7, 5000);
        project1.setEndDate(LocalDate.of(2021, 12, 31));

        Project project3 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 7, 5000);
        project3.setEndDate(LocalDate.of(2022, 1, 31));

        UserProfile profile1 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile1);
        LocalDate startDate1 = LocalDate.of(2021, 12, 1);
        LocalDate endDate1 = LocalDate.of(2021, 12, 15);
        Resource resource1 = new Resource(user1, startDate1, endDate1, 100, .25);

        LocalDate copyStartDate1 = LocalDate.of(2022, 1, 1);
        LocalDate copyEndDate1 = LocalDate.of(2022, 1, 15);
        Resource copyResource1 = new Resource(user1, copyStartDate1, copyEndDate1, 100, .5);

        LocalDate copy2StartDate1 = LocalDate.of(2022, 1, 16);
        LocalDate copy2EndDate1 = LocalDate.of(2022, 1, 31);
        Resource copy2Resource1 = new Resource(user1, copy2StartDate1, copy2EndDate1, 100, 1);

        UserProfile profile2 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "ghi", "ghi", "photo", profile2);
        LocalDate startDate2 = LocalDate.of(2021, 12, 1);
        LocalDate endDate2 = LocalDate.of(2021, 12, 15);
        Resource resource2 = new Resource(user2, startDate2, endDate2, 100, .25);

        LocalDate copyStartDate2 = LocalDate.of(2022, 1, 1);
        LocalDate copyEndDate2 = LocalDate.of(2022, 1, 15);
        Resource copyResource2 = new Resource(user2, copyStartDate2, copyEndDate2, 100, .5);

        UserProfile profile3 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile3);
        LocalDate startDate3 = LocalDate.of(2021, 12, 16);
        LocalDate endDate3 = LocalDate.of(2021, 12, 31);
        Resource resource3 = new Resource(user3, startDate3, endDate3, 100, .5);

        LocalDate copyStartDate3 = LocalDate.of(2021, 12, 1);
        LocalDate copyEndDate3 = LocalDate.of(2021, 12, 15);
        Resource copyResource3 = new Resource(user3, copyStartDate3, copyEndDate3, 100, .25);

        UserProfile profile4 = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile4);
        LocalDate startDate4 = LocalDate.of(2021, 12, 1);
        LocalDate endDate4 = LocalDate.of(2021, 12, 15);
        Resource resource4 = new Resource(user4, startDate4, endDate4, 100, .25);

        LocalDate copyStartDate4 = LocalDate.of(2021, 12, 16);
        LocalDate copyEndDate4 = LocalDate.of(2021, 12, 31);
        Resource copyResource4 = new Resource(user4, copyStartDate4, copyEndDate4, 100, .5);

        project1.addResource(resource1);
        project3.addResource(copyResource1);
        project3.addResource(copy2Resource1);

        project1.addResource(resource2);
        project3.addResource(copyResource2);

        project1.addResource(resource3);
        project1.addResource(copyResource3);

        project1.addResource(resource4);
        project1.addResource(copyResource4);


        ProjectTeamTest test = new ProjectTeamTest();

        company.getProjectStore().addProject(test.getProj1());
        company.getProjectStore().addProject(test.getProj2());
        company.getProjectStore().addProject(test.getProj3());
        company.getProjectStore().addProject(test.getProj4());

        // Act
        List<Project> projectList = company.getProjectStore().getCurrentProjectListByUserEmail("manuelmartins@beaver.com");

        int sizeExpected = projectList.size();

        // Assert
        assertEquals(1, sizeExpected);*/
    }

}
