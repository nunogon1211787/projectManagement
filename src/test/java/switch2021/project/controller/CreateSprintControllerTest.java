package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.model.Project.*;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.ProjectStore;

import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateSprintControllerTest {

    private ProjectStore projectStore;

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
        projectStore.saveNewProject(currentProject);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("joana", "joana@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource joana = new Resource(user2, startDateMm, endDateMm, new CostPerHour(100), new PercentageOfAllocation(1));
        currentProject.getProjectTeam().saveResource(joana);

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
        Company company = new Company();
        CreateSprintController controller = new CreateSprintController(company);
        this.projectStore = company.getProjectStore();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        projectStore.saveNewProject(currentProject);

        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusDays(7));
        projectStore.saveNewProject(proj1);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("joana", "joana@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource joana = new Resource(user2, startDateMm, endDateMm, new CostPerHour(100), new PercentageOfAllocation(1));
        currentProject.getProjectTeam().saveResource(joana);
        proj1.getProjectTeam().saveResource(joana);

        controller.getCurrentProjectListByUserEmail("joana@beaver.com");
        //Act
        Project projectActual = controller.getProject("Project_2022_1");
        //Assert
        assertEquals(currentProject, projectActual);
    }

    @Test
    @DisplayName("Test to create a sprint")
    public void createASprint() {

        //Arrange and Act
        Company company = new Company();
        CreateSprintController controller = new CreateSprintController(company);
        this.projectStore = company.getProjectStore();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        projectStore.saveNewProject(currentProject);

        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusDays(7));
        proj1.setSprintDuration(new SprintDuration(31));
        projectStore.saveNewProject(proj1);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("joana", "joana@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource joana = new Resource(user2, startDateMm, endDateMm, new CostPerHour(100), new PercentageOfAllocation(1));
        currentProject.getProjectTeam().saveResource(joana);
        proj1.getProjectTeam().saveResource(joana);

        controller.getCurrentProjectListByUserEmail("joana@beaver.com");
        controller.getProject(proj1.getProjectCode().getCode());
        Sprint sprintC = controller.createSprint("Sprint44");
        proj1.getSprintList().saveSprint(sprintC);

        //Assert
        assertEquals(sprintC, proj1.getSprintList().findSprintById(sprintC.getIdSprint()));
    }

    @Test
    @DisplayName("Test to save a sprint - Success")
    public void saveASprintSuccess() {

        //Arrange and Act
        Company company = new Company();
        CreateSprintController controller = new CreateSprintController(company);
        this.projectStore = company.getProjectStore();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        projectStore.saveNewProject(currentProject);

        Project proj1 = company.getProjectStore().createProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusDays(7));
        proj1.setSprintDuration(new SprintDuration(31));
        projectStore.saveNewProject(proj1);

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("joana", "joana@beaver.com", "tester",
                "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource joana = new Resource(user2, startDateMm, endDateMm, new CostPerHour(100), new PercentageOfAllocation(1));
        currentProject.getProjectTeam().saveResource(joana);
        proj1.getProjectTeam().saveResource(joana);

        controller.getCurrentProjectListByUserEmail("joana@beaver.com");
        controller.getProject(proj1.getProjectCode().getCode());

        Sprint sprintC = controller.createSprint("Sprint44");

        //Assert
        assertTrue(controller.saveSprint(sprintC));
    }

}
