package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSprintControllerTest {

    @Test
    @DisplayName("To search a project")
    public void getProject() {
        //Arrange
        //Company company = App.getInstance().getCompany();
        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.of(2022, 1, 1);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
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
        //company.getProjectStore().removeProject(project);
    }

    @Test
    @DisplayName("Test to create a sprint")
    public void createAndSaveSprint() {

        //Arrange
        Company company = new Company();

        Project proj;

        LocalDate date = LocalDate.of(2021, 12, 12);

        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
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
        //company.getProjectStore().removeProject(proj);
    }
}
