package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GetProjectListControllerTest {

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

        proj1 = company.getProjectStore().createProject( "prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 3000);
        proj1.setEndDate(LocalDate.of(2021, 11, 30));

        proj2 = company.getProjectStore().createProject( "prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj2.setEndDate(LocalDate.of(2021, 11, 30));

        proj3 = company.getProjectStore().createProject( "prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2021, 11, 1), 2, 2000);
        proj3.setEndDate(LocalDate.of(2021, 11, 30));

        currentProject = company.getProjectStore().createProject( "prototype4", "proj4Prototype", customer,
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

    @Test
    public void getProjectListSizeSuccessWith2Projects() {
        //Arrange
        Company company = new Company();
        GetProjectListController controller = new GetProjectListController(company);
        ProjectStore projectStore = company.getProjectStore();
        projectStore.addProject(this.proj1);
        projectStore.addProject(this.proj2);
        // Act
        List<Project> projectList = controller.getProjectList();
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(2, sizeExpected);

        projectStore.removeProject(this.proj1);
        projectStore.removeProject(this.proj2);
    }

    @Test
    public void getProjectListSizeSuccessEmptyList() {
        //Arrange
        Company company = new Company();
        GetProjectListController controller = new GetProjectListController(company);
        // Act
        List<Project> projectList = controller.getProjectList();
        // Assert
        assertTrue(projectList.isEmpty());
    }

    @Test
    public void getProjectListSuccessCorrectList() {
        //Arrange
        Company company = new Company();
        GetProjectListController controller = new GetProjectListController(company);
        ProjectStore p = company.getProjectStore();
        p.addProject(this.proj3);
        // Act
        List<Project> projectListResult = controller.getProjectList();

        List<Project> projectListExpected = new ArrayList<>();
        projectListExpected.add(this.proj3);
        // Assert
        //verifica se o resource está na lista:
        assertTrue(projectListResult.get(0).hasProjectTeamMember("manueloliveira@beaver.com"));
        //verifica se têm a mesma ProjectTeam:
        assertEquals(projectListExpected.get(0).getProjectTeam(), projectListResult.get(0).getProjectTeam());
        //verifica se têm o mesmo Project:
        assertEquals(projectListResult.indexOf(0), projectListExpected.indexOf(0));

        p.removeProject(this.proj3);
    }
}
