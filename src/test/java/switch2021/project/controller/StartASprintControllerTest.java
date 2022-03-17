package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.dto.StartASprintDTO;
import switch2021.project.model.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StartASprintControllerTest {

@Test
@DisplayName("Test to Start a Sprint (Success), invoking the controller")

    public void startASprintGlobalSuccess() {

        //Arrange
        Company company = new Company();

        StartASprintController startASprintController = new StartASprintController(company);

        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        //Project
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Resource 1
        SystemUser joana1 = new SystemUser("joana1", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        proj1.getProjectTeam().saveResource(joana1R);

        //Resource 2
        SystemUser joana2 = new SystemUser("joana2", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
        proj1.getProjectTeam().saveResource(joana2R);

        //Resource 3
        SystemUser joana3 = new SystemUser("joana3", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        proj1.getProjectTeam().saveResource(joana3R);

        //Resource 4
        SystemUser joana4 = new SystemUser("joana4", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana4R);

        //Create a Sprint
        proj1.getSprints().saveSprint(proj1.getSprints().createSprint("Sprint_1", LocalDate.of(2022, 1, 1),
                proj1.getSprintDuration()));

        //Act

        StartASprintDTO sprintDTO = new StartASprintDTO(proj1.getCode(),
                proj1.getSprints().getSprintList().get(0).getIdSprint(),
                LocalDate.of(2022, 1, 16));

        //Assert
        assertTrue(startASprintController.startASprint(sprintDTO));
    }

    @Test
    @DisplayName("Test to Start a Sprint (Fail), invoking the controller")

    public void startASprintGlobalFail() {

        //Arrange
        Company company = new Company();

        StartASprintController startASprintController = new StartASprintController(company);

        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");

        //Project
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2022, 12, 31));
        company.getProjectStore().saveNewProject(proj1);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        //Resource 1
        SystemUser joana1 = new SystemUser("joana1", "joana1@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej1 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej1 = LocalDate.of(2022, 1, 30);
        Resource joana1R = proj1.createResource(joana1, startDatej1, endDatej1, 100, .5);
        joana1R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana1R);

        //Resource 2
        SystemUser joana2 = new SystemUser("joana2", "joana2@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej2 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej2 = LocalDate.of(2022, 1, 30);
        Resource joana2R = proj1.createResource(joana2, startDatej2, endDatej2, 100, 1);
        joana2R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana2R);

        //Resource 3
        SystemUser joana3 = new SystemUser("joana3", "joana3@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej3 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej3 = LocalDate.of(2022, 12, 31);
        Resource joana3R = proj1.createResource(joana3, startDatej3, endDatej3, 100, .5);
        joana3R.setRole(company.getProjectRoleStore().getProjectRole("Project Manager"));
        proj1.getProjectTeam().saveResource(joana3R);

        //Resource 4
        SystemUser joana4 = new SystemUser("joana4", "joana4@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDatej4 = LocalDate.of(2022, 1, 1);
        LocalDate endDatej4 = LocalDate.of(2022, 12, 31);
        Resource joana4R = proj1.createResource(joana4, startDatej4, endDatej4, 100, .3333);
        joana4R.setRole(company.getProjectRoleStore().getProjectRole("Team Member"));
        proj1.getProjectTeam().saveResource(joana4R);

        //Create a Sprint
        proj1.getSprints().saveSprint(proj1.getSprints().createSprint("Sprint_1", LocalDate.of(2022, 1, 1),
                proj1.getSprintDuration()));

        //Act

        StartASprintDTO sprintDTO = new StartASprintDTO(proj1.getCode(),
                proj1.getSprints().getSprintList().get(0).getIdSprint(),
                LocalDate.of(2023, 1, 1));

        //Assert
        assertFalse(startASprintController.startASprint(sprintDTO));
    }

}
