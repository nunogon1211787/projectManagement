package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintStore;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSprintControllerTest {


   /* @Test
    @DisplayName("Controller Test, to get a list of projects")
    public void getProjectList() {

        //Arrange
        Company company = new Company();
        CreateSprintController controllerTest1 = new CreateSprintController();
        List<Project> projectStore = controllerTest1.getProjectList();
        //Act
        List<Project> projectStore1 = new ArrayList<>();
        //Assert
        assertEquals(projectStore, projectStore1);
    }*/

    @Test
    @DisplayName("To search a project")
    public void getProject() {

        //Arrange
        Company company = App.getInstance().getCompany();

        Project project;

        LocalDate date = LocalDate.of(2022, 1, 1);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        company.getProjectStore().addProject(project);
        //Act
        CreateSprintController controllerTest1 = new CreateSprintController();
        Project project1 = controllerTest1.getProject(company, "123testcode");
        //Assert
        assertEquals(project, project1);

    }

    //@Test
    //@DisplayName("Test to create a sprint")
    //public void createSprint() {

        //Arrange
        //Company company = new Company();

        //Project proj;

        //LocalDate date = LocalDate.of(2021, 12, 12);

        //company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        //company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));
        //Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        //Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        //BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        //proj = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
        //        typo, sector, date, 7, 5000);

        //proj.setSprintDuration(2);

        //Sprint sprint1 = proj.getSprintStore().createSprint("Sprint_1", LocalDate.of(2022, 1, 1), 2);
        //proj.getSprintStore().addSprint(sprint1);
        //company.getProjectStore().addProject(proj);

        //CreateSprintController controllerTest1 = new CreateSprintController();
        //Sprint sprint = controllerTest1.createSprint("Sprint_1", LocalDate.of(2022, 1, 1));
        //controllerTest1.getProject(company, "123testcode");

        //Assert
        //assertEquals(sprint, sprint1);


 }



