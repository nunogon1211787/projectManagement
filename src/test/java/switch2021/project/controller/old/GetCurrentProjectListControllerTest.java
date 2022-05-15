package switch2021.project.controller.old;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.controller.old.GetCurrentProjectListController;
import switch2021.project.model.*;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Resource.old.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.ProjectStore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetCurrentProjectListControllerTest {
/*    @Test
    @DisplayName("Size test with one project")
    public void getCurrentProjectListByUserEmailSuccessWith1Project() {
        //Arrange
        Company company = new Company();
        GetCurrentProjectListController controller = new GetCurrentProjectListController(company);

        ProjectStore projectStore = company.getProjectStore();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createAndSaveProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource manuelmartins = new Resource(user2, startDateMm, endDateMm, new CostPerHour(100), new PercentageOfAllocation(1));
        currentProject.getProjectTeam().saveResource(manuelmartins);
        // Act
        List<Project> projectListActual = controller.getCurrentProjectsByUserEmail("manuelmartins@beaver.com");
        int sizeExpected = projectListActual.size();
        // Assert
        assertEquals(1, sizeExpected);
    }

    @Test
    @DisplayName("Size test with two project")
    public void getCurrentProjectListByUserEmailSuccessWith2Project() {
        //Arrange
        Company company = new Company();
        GetCurrentProjectListController controller = new GetCurrentProjectListController(company);

        ProjectStore projectStore = company.getProjectStore();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createAndSaveProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource manuelmartins = new Resource(user2, startDateMm, endDateMm, new CostPerHour(100), new PercentageOfAllocation(.5));
        currentProject.getProjectTeam().saveResource(manuelmartins);

        Project proj1 = company.getProjectStore().createAndSaveProject("prototype1", "proj1Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 3000);
        proj1.setEndDate(LocalDate.now().plusDays(7));
        proj1.getProjectTeam().saveResource(manuelmartins);
        // Act
        List<Project> projectListActual = controller.getCurrentProjectsByUserEmail("manuelmartins@beaver.com");
        int sizeExpected = projectListActual.size();
        // Assert
        assertEquals(2, sizeExpected);
    }

 */

    @Test
    @DisplayName("Size test empty")
    public void getCurrentProjectListByUserEmailFailEmptyList() {
        //Arrange
        Company company = new Company();
        GetCurrentProjectListController controller = new GetCurrentProjectListController(company);
        // Act
        List<Project> projectList = controller.getCurrentProjectsByUserEmail("manuelmartins@beaver.com");
        // Assert
        assertTrue(projectList.isEmpty());
    }

/*    @Test
    @DisplayName("Check both lists are equal")
    public void getCurrentProjectListByUserEmailSuccessCorrectList() {
        //Arrange
        Company company = new Company();
        GetCurrentProjectListController controller = new GetCurrentProjectListController(company);

        ProjectStore projectStore = company.getProjectStore();
        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project currentProject = company.getProjectStore().createAndSaveProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.now().minusDays(7), 2, 4000);
        currentProject.setEndDate(LocalDate.now().plusDays(7));
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user2 = new SystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo.png", profile.getUserProfileId());
        LocalDate startDateMm = LocalDate.now().minusDays(7);
        LocalDate endDateMm = LocalDate.now().plusDays(7);
        Resource manuelmartins = new Resource(user2, startDateMm, endDateMm, new CostPerHour(100), new PercentageOfAllocation(.5));
        currentProject.getProjectTeam().saveResource(manuelmartins);

        List<Project> projectListExpected = new ArrayList<>();
        projectListExpected.add(currentProject);
        // Act
        List<Project> projectListActual = controller.getCurrentProjectsByUserEmail("manuelmartins@beaver.com");
        // Assert
        assertEquals(projectListExpected, projectListActual);
    }

 */
}
