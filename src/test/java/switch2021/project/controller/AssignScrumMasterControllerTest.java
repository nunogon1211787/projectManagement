package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.mapper.ProjectTeamMapper;
import switch2021.project.mapper.ProjectsMapper;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class AssignScrumMasterControllerTest {

    @Test
    public void getProjectFailTest() {
        //Assert
        assertThrows(NullPointerException.class, ()->{
            //Arrange
            Company company = new Company();
            ProjectsMapper projectsMapper = new ProjectsMapper();
            ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();
            AssignScrumMasterController controller = new AssignScrumMasterController(company, projectsMapper,projectTeamMapper);
            Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
            company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
//            company.getCustomerStore().saveNewCustomer(customer);
            company.getBusinessSectorStore().createAndAddBusinessSector("it");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            Project proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
            company.getProjectStore().saveNewProject(proj1);
            //Act
            controller.getProject("proj_2022_1"); //Project is not save at ProjectStore
        });
    }

    @Test
    void assignScrumMasterTestSuccess() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();
        AssignScrumMasterController controller = new AssignScrumMasterController(company, projectsMapper,projectTeamMapper);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
//        company.getCustomerStore().saveNewCustomer(customer);
        company.getBusinessSectorStore().createAndAddBusinessSector("it");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setSprintDuration(new SprintDuration(14));
        company.getProjectStore().saveNewProject(proj1);
        Sprint sprint1 = proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name 1", 14);
        sprint1.setStartDate(LocalDate.now());
        Sprint sprint2 = proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 2", "Sprint Name 2", 14);
        sprint2.setStartDate(LocalDate.of(2022, 4, 13));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Create resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        proj1.getProjectTeam().saveResource(manuelbras);
        //Create resource 2
        SystemUser user2 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user2, startDateMj, endDateMj, 100, .5);
        proj1.getProjectTeam().saveResource(manueljose);
        //Create resource 3
        SystemUser user3 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(51);
        Resource manueloliveira = proj1.getProjectTeam().createResource(user3, startDateMo, endDateMo, 100, .3333);
        proj1.getProjectTeam().saveResource(manueloliveira);
        //Create resource 4
        SystemUser user4 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = proj1.getProjectTeam().createResource(user4, startDateMf, endDateMf, 100, 1);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        //Act
        controller.getProject("project_2022_1");
        controller.getResource("manueljose");
        //Asserts
        assertTrue(controller.assignRole("manueljose", "Scrum Master"));
        assertEquals(5,proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test
    void assignScrumMasterTestSuccessWithRoleDefined() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();
        AssignScrumMasterController controller = new AssignScrumMasterController(company, projectsMapper,projectTeamMapper);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        company.getBusinessSectorStore().createAndAddBusinessSector("it");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setSprintDuration(new SprintDuration(14));
        company.getProjectStore().saveNewProject(proj1);
        Sprint sprint1 = proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 1", "Sprint Name 1", 14);
        sprint1.setStartDate(LocalDate.now());
        Sprint sprint2 = proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint 2","Sprint Name 2", 14);
        sprint2.setStartDate(LocalDate.of(2022, 4, 13));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        //Create resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        manuelbras.setRole(company.getProjectRoleStore().getProjectRole("Scrum Master"));
        proj1.getProjectTeam().saveResource(manuelbras);
        //Create resource 2
        SystemUser user2 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user2, startDateMj, endDateMj, 100, .5);
        proj1.getProjectTeam().saveResource(manueljose);
        //Create resource 3
        SystemUser user3 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMo = LocalDate.now().minusWeeks(1);
        LocalDate endDateMo = LocalDate.now().plusWeeks(51);
        Resource manueloliveira = proj1.getProjectTeam().createResource(user3, startDateMo, endDateMo, 100, .3333);
        proj1.getProjectTeam().saveResource(manueloliveira);
        //Create resource 4
        SystemUser user4 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMf = LocalDate.of(2021, 11, 16);
        LocalDate endDateMf = LocalDate.of(2022, 11, 30);
        Resource manuelfernandes = proj1.getProjectTeam().createResource(user4, startDateMf, endDateMf, 100, 1);
        proj1.getProjectTeam().saveResource(manuelfernandes);
        //Act
        controller.getProject("project_2022_1");
        controller.getResource("manueljose");
        //Asserts
        assertTrue(controller.assignRole("manueljose", "Scrum Master"));
        assertEquals(6,proj1.getProjectTeam().getProjectTeamList().size());
    }

    @Test
    void assignRoleTestSprintNullFailure() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            ProjectsMapper projectsMapper = new ProjectsMapper();
            ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();
            AssignScrumMasterController controller = new AssignScrumMasterController(company, projectsMapper,projectTeamMapper);
            Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
            company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
            company.getProjectStore().saveNewProject(proj1);
            proj1.setSprintDuration(new SprintDuration(14));
            UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
            // Create Resource 1
            SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
            LocalDate startDateMb = LocalDate.of(2021, 11, 1);
            LocalDate endDateMb = LocalDate.of(2022, 11, 15);
            Resource manuelbras = proj1.createResource(user1, startDateMb, endDateMb, 100, .5);
            proj1.getProjectTeam().saveResource(manuelbras);
            // Create Resource 2
            SystemUser user2 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
            LocalDate startDateMj = LocalDate.of(2021, 11, 1);
            LocalDate endDateMj = LocalDate.of(2022, 1, 31);
            Resource manueljose = proj1.createResource(user2, startDateMj, endDateMj, 100, .5);
            proj1.getProjectTeam().saveResource(manueljose);
            // Create Resource 3
            SystemUser user3 = new SystemUser("manueloliveira", "manueloliveira@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
            LocalDate startDateMo = LocalDate.now().minusWeeks(1);
            LocalDate endDateMo = LocalDate.now().plusWeeks(51);
            Resource manueloliveira = proj1.createResource(user3, startDateMo, endDateMo, 100, .3333);
            proj1.getProjectTeam().saveResource(manueloliveira);
            // Create Resource 4
            SystemUser user4 = new SystemUser("manuelfernandes", "manuelfernandes@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
            LocalDate startDateMf = LocalDate.of(2021, 11, 16);
            LocalDate endDateMf = LocalDate.of(2022, 11, 30);
            Resource manuelfernandes = proj1.createResource(user4, startDateMf, endDateMf, 100, 1);
            proj1.getProjectTeam().saveResource(manuelfernandes);

            //Act
            controller.getProject("project_2022_1");
            controller.getResource("manueljose");
            controller.assignRole("manueljose", "Scrum Master"); //Resource without possible dates
        });
    }

    @Test
    void assignRoleTestResourceNullFailure() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            ProjectsMapper projectsMapper = new ProjectsMapper();
            ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();
            AssignScrumMasterController controller = new AssignScrumMasterController(company, projectsMapper,projectTeamMapper);
            Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
            company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
            company.getProjectStore().saveNewProject(proj1);
            proj1.setSprintDuration(new SprintDuration(14));
            proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint Current", "Current Sprint", 2);
            proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint Next", "Next Sprint", 2);
            //Act
            controller.getProject("project_2022_1");
            controller.getResource("manueljose");
            controller.assignRole(null, "Scrum Master");
        });
    }

    @Test
    void assignRoleTestResourceNullFailure2() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            Company company = new Company();
            ProjectsMapper projectsMapper = new ProjectsMapper();
            ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();
            AssignScrumMasterController controller = new AssignScrumMasterController(company, projectsMapper,projectTeamMapper);
            Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
            company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
            Customer customer = company.getCustomerStore().getCustomerByName("isep");
            BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
            Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                    typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
            company.getProjectStore().saveNewProject(proj1);
            proj1.setSprintDuration(new SprintDuration(14));
            proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint Current", "Sprint Current", 2);
            proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint Next", "Sprint Next", 2);
            //Act
            controller.getProject("project_2022_1");
            controller.getResource("manueljose");
            controller.assignRole("mabueljose", "Scrum Master");
        });
    }

    @Test
    void assignRoleWrongName() {
        //Arrange
        Company company = new Company();
        ProjectsMapper projectsMapper = new ProjectsMapper();
        ProjectTeamMapper projectTeamMapper = new ProjectTeamMapper();
        AssignScrumMasterController controller = new AssignScrumMasterController(company, projectsMapper,projectTeamMapper);
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        company.getCustomerStore().createAndAddCustomer("isep","xxx@sss.sss", 123456789);
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setSprintDuration(new SprintDuration(14));
        company.getProjectStore().saveNewProject(proj1);
        proj1.setSprintDuration(new SprintDuration(14));
        Sprint current = proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint Current", "Sprint Current", 14);
        current.setStartDate(LocalDate.now());
        Sprint next = proj1.getSprintList().createAndSaveSprint(1, "Project_2022_1_Sprint Next", "Sprint Next", 14);
        next.setStartDate(LocalDate.of(2022, 4, 13));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
             //Create resource 1
        SystemUser user1 = new SystemUser("manuelbras", "manuelbras@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMb = LocalDate.of(2021, 11, 1);
        LocalDate endDateMb = LocalDate.of(2022, 11, 15);
        Resource manuelbras = proj1.getProjectTeam().createResource(user1, startDateMb, endDateMb, 100, .5);
        proj1.getProjectTeam().saveResource(manuelbras);
            //Create resource 2
        SystemUser user2 = new SystemUser("manueljose", "manueljose@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMj = LocalDate.of(2021, 11, 1);
        LocalDate endDateMj = LocalDate.of(2022, 11, 15);
        Resource manueljose = proj1.getProjectTeam().createResource(user2, startDateMj, endDateMj, 100, .5);
        proj1.getProjectTeam().saveResource(manueljose);
        //Act
        controller.getProject("project_2022_1");
        controller.getResource("manuelbras");
        //Assert
        assertFalse(controller.assignRole("manueljose","Scrum Master"));
    }
}