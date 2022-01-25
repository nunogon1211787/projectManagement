package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSprintControllerTest {


    @Test
    @DisplayName("To search the list of projects that the PM is current in")
    public void getCurrentProjectListByUserEmailSuccess() {
        //Arrange
        Company company = new Company();
        CreateSprintController controller = new CreateSprintController(company);

        ProjectStore projectStore = company.getProjectStore();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("joana", "joana@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource joana = new Resource(user2, startDateMm, endDateMm, 100, 1);
        currentProject.getProjectTeam().addResourceToTeam(joana);
        projectStore.saveNewProject(currentProject);
        // Act
        List<Project> projectListActual = controller.getCurrentProjectListByUserEmail("joana@beaver.com");
        int sizeExpected = projectListActual.size();
        // Assert
        assertEquals(1, sizeExpected);
    }


    @Test
    @DisplayName("To search a project")
    public void getProject() {
        //Arrange
        //Company company = App.getInstance().getCompany();
        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.of(2022, 1, 1);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        company.getProjectStore().saveNewProject(project);
        //Act
        CreateSprintController controllerTest1 = new CreateSprintController(company);
        Project project1 = controllerTest1.getProject(company, "Project_2022_1");
        //Assert
        assertEquals(project, project1);
    }

    @Test
    @DisplayName("Test to create a sprint")
    public void createAndSaveSprint() {

        //Arrange
        Company company = new Company();

        Project proj;

        LocalDate date = LocalDate.of(2021, 12, 12);

        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste"));
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        proj = company.getProjectStore().createProject( "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);

        proj.setSprintDuration(2);

        company.getProjectStore().saveNewProject(proj);

        CreateSprintController controllerTest1 = new CreateSprintController(company);
        controllerTest1.getProject(company, "Project_2022_1");
        Sprint sprint = controllerTest1.createSprint("Sprint_1", LocalDate.of(2022, 1, 1));
        controllerTest1.saveSprint(sprint);

        //Assert
        assertEquals(sprint, proj.getSprintStore().getSprint(1));
    }
}
