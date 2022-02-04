package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignProductOwnerControllerTest {


    @Test
    public void controllerFailTest() {
        //Arrange
        Company company = new Company();
        AssignProductOwnerController controller = new AssignProductOwnerController(company);
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep","xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        //Act
        Project projectTest = controller.getProject("proj_2022_1"); //Project is not save at ProjectStore
        //Assert
        assertNotEquals(proj1,projectTest);
    }

    @Test
    void assignProductOwnerTestSuccess() {
        //Arrange
        Company company = new Company();
        AssignProductOwnerController controller = new AssignProductOwnerController(company);
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep", "xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        company.getProjectStore().saveNewProject(proj1);
        Sprint sprint1 = proj1.getSprints().createSprint("Sprint1", LocalDate.now().minusWeeks(1), 2);
        proj1.getSprints().saveSprint(sprint1);
        Sprint sprint2 = proj1.getSprints().createSprint("Sprint2", LocalDate.now().plusWeeks(1), 2);
        proj1.getSprints().saveSprint(sprint2);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Create resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        proj1.getProjectTeam().saveResource(manuelbras);
        //Create resource 2
        SystemUser user2 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user2, startDateMj, endDateMj, 100, .5);
        proj1.getProjectTeam().saveResource(manueljose);
        //Create resource 3
        SystemUser user3 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(51);
        Resource manueloliveira = proj1.getProjectTeam().createResource(user3, startDateMo, endDateMo, 100, .3333);
        proj1.getProjectTeam().saveResource(manueloliveira);
        //Create resource 4
        SystemUser user4 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = proj1.getProjectTeam().createResource(user4, startDateMf, endDateMf, 100, 1);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        //Act
        controller.getProject("project_2022_1");
        controller.getResource("manueljose");
        controller.getProjectTeamList();
        //Asserts
        assertTrue(controller.assignRole("manueljose", "Product Owner"));
        assertEquals(5,proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test
    void assignProductOwnerTestSuccessWithRoleDefined() {
        //Arrange
        Company company = new Company();
        AssignProductOwnerController  controller = new AssignProductOwnerController(company);
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().createCustomer("isep", "xxx@sss.sss");
        company.getCustomerStore().saveNewCustomer(customer);
        BusinessSector sector = company.getBusinessSectorStore().createBusinessSector("it");
        company.getBusinessSectorStore().addBusinessSector(sector);
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        company.getProjectStore().saveNewProject(proj1);
        Sprint sprint1 = proj1.getSprints().createSprint("Sprint1", LocalDate.now().minusWeeks(1), 2);
        proj1.getSprints().saveSprint(sprint1);
        Sprint sprint2 = proj1.getSprints().createSprint("Sprint2", LocalDate.now().plusWeeks(1), 2);
        proj1.getSprints().saveSprint(sprint2);
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Create resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Product Owner"));
        proj1.getProjectTeam().saveResource(manuelbras);
        //Create resource 2
        SystemUser user2 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user2, startDateMj, endDateMj, 100, .5);
        proj1.getProjectTeam().saveResource(manueljose);
        //Create resource 3
        SystemUser user3 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(51);
        Resource manueloliveira = proj1.getProjectTeam().createResource(user3, startDateMo, endDateMo, 100, .3333);
        proj1.getProjectTeam().saveResource(manueloliveira);
        //Create resource 4
        SystemUser user4 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = proj1.getProjectTeam().createResource(user4, startDateMf, endDateMf, 100, 1);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        //Act
        controller.getProject("project_2022_1");
        controller.getResource("manueljose");
        controller.getProjectTeamList();
        //Asserts
        assertTrue(controller.assignRole("manueljose", "Product Owner"));
        assertEquals(6,proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test
    void assignRoleTestSprintNullFailure() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            AssignProductOwnerController controller = new AssignProductOwnerController(company);
            Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
            Customer customer = company.getCustomerStore().createCustomer("isep", "isep@gmail.pt");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
            proj1.setSprintDuration(2);
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            // Create Resource 1
            SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            LocalDate startDateMb = LocalDate.of(2021, 11, 1);
            LocalDate endDateMb = LocalDate.of(2022, 11, 15);
            Resource manuelbras = proj1.createResource(user1, startDateMb, endDateMb, 100, .5);
            proj1.getProjectTeam().saveResource(manuelbras);
            // Create Resource 2
            SystemUser user2 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            LocalDate startDateMj = LocalDate.of(2021, 11, 1);
            LocalDate endDateMj = LocalDate.of(2022, 1, 31);
            Resource manueljose = proj1.createResource(user2, startDateMj, endDateMj, 100, .5);
            proj1.getProjectTeam().saveResource(manueljose);
            // Create Resource 3
            SystemUser user3 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            LocalDate startDateMo = LocalDate.now().minusWeeks(1);
            LocalDate endDateMo = LocalDate.now().plusWeeks(51);
            Resource manueloliveira = proj1.createResource(user3, startDateMo, endDateMo, 100, .3333);
            proj1.getProjectTeam().saveResource(manueloliveira);
            // Create Resource 4
            SystemUser user4 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
            LocalDate startDateMf = LocalDate.of(2021, 11, 16);
            LocalDate endDateMf = LocalDate.of(2022, 11, 30);
            Resource manuelfernandes = proj1.createResource(user4, startDateMf, endDateMf, 100, 1);
            proj1.getProjectTeam().saveResource(manuelfernandes);

            //Act
            controller.getProject("project_2022_1");
            controller.getResource("manueljose@beaver.com");
            controller.getProjectTeamList();
            controller.assignRole("manueljose@beaver.com", "Product Owner"); //Resource without possible dates
        });
    }

    @Test
    void assignRoleTestResourceNullFailure() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            AssignProductOwnerController controller = new AssignProductOwnerController(company);
            Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
            Customer customer = company.getCustomerStore().createCustomer("isep", "isep@gmail.pt");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
            proj1.setSprintDuration(2);
            Sprint current = proj1.getSprints().createSprint("Current", LocalDate.now().minusWeeks(2).plusDays(1), 2);
            proj1.getSprints().saveSprint(current);
            Sprint next = proj1.getSprints().createSprint("Next", LocalDate.now().plusDays(1), 2);
            proj1.getSprints().saveSprint(next);
            //Act
            controller.getProject("project_2022_1");
            controller.getResource("manueljose@beaver.com");
            controller.getProjectTeamList();
            controller.assignRole(null, "Product Owner");
        });
    }
}
