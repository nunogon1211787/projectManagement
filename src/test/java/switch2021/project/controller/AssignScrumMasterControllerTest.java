package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AssignScrumMasterControllerTest {

    @Test
    void assignScrumMasterTestSuccess() {
        //Input
        Company company = new Company();
        AssignScrumMasterController controller = new AssignScrumMasterController(company);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        Project proj1 = controller.getProject("proj_2022_1");
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");

        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = new Resource(user1, startDateMb, endDateMb, 100, .5);

        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = new Resource(user3, startDateMj, endDateMj, 100, .5);

        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(51);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);

        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = new Resource(user5, startDateMf, endDateMf, 100, 1);

        proj1.getProjectTeam().addResourceToTeam(manuelbras);
        proj1.getProjectTeam().addResourceToTeam(manueljose);
        proj1.getProjectTeam().addResourceToTeam(manueloliveira);
        proj1.getProjectTeam().addResourceToTeam(manuelfernandes);

        //Arrange
        LocalDate sDate;
        try {
            sDate = proj1.getSprintList().getCurrentSprint().getEndDate().plusDays(1);
        }catch (NullPointerException e){
            sDate = LocalDate.now();
        }
        int sDuration = proj1.getSprintDuration();
        ProjectRole sMaster = company.getProjectRoleStore().getProjectRole("Scrum Master");

        //Asserts
        assertTrue(proj1.getProjectTeam().assignProjectRole(manueljose, sDate, sDuration, sMaster));
    }

    @Test
    void assignRoleTestFailure() {
        //Input
        Company co = new Company();
        Typology typo = co.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = co.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = co.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project proj1 = co.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setSprintDuration(2);

        UserProfile profile = co.getUserProfileStore().getUserProfile("Visitor");

        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = new Resource(user1, startDateMb, endDateMb, 100, .5);

        SystemUser user3 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 1, 31);
        Resource manueljose = new Resource(user3, startDateMj, endDateMj, 100, .5);

        SystemUser user4 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(51);
        Resource manueloliveira = new Resource(user4, startDateMo, endDateMo, 100, .3333);

        SystemUser user5 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = new Resource(user5, startDateMf, endDateMf, 100, 1);

        proj1.getProjectTeam().addResourceToTeam(manuelbras);
        proj1.getProjectTeam().addResourceToTeam(manueljose);
        proj1.getProjectTeam().addResourceToTeam(manueloliveira);
        proj1.getProjectTeam().addResourceToTeam(manuelfernandes);

        //Arrange
        LocalDate sDate;
        try {
            sDate = proj1.getSprintList().getCurrentSprint().getEndDate().plusDays(1);
        }catch (NullPointerException e){
            sDate = LocalDate.now();
        }
        int sDuration = proj1.getSprintDuration();
        ProjectRole sMaster = co.getProjectRoleStore().getProjectRole("Scrum Master");

        //Asserts
        assertFalse(proj1.getProjectTeam().assignProjectRole(manueljose, sDate, sDuration, sMaster)); //Resource without possible dates
    }
}