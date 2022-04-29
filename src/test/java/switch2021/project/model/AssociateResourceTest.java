package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Resource.Resource;
import switch2021.project.model.SystemUser.SystemUser;
import switch2021.project.model.Typology.Typology;
import switch2021.project.model.UserProfile.UserProfile;
import switch2021.project.model.valueObject.*;
import switch2021.project.repositories.ProjectStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AssociateResourceTest {

    private UserProfile userProfile;
    private Project proj;
    private Project proj1;
    private Project proj2;
    private Project proj3;
    private ProjectStore projectList;

    @BeforeEach
    public void init() {
        Company company = new Company();
        userProfile = company.getUserProfileStore().getUserProfile("Visitor");

        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().createAndAddBusinessSector("sector");
        company.getCustomerStore().createAndAddCustomer("Test", "Teste@teste.com", 123456789);

        Typology typo = company.getTypologyRepository().findTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        proj = company.getProjectStore().createAndSaveProject("prototype", "test56", customer,
                typo, sector, date, 7, 5000);
        proj1 = company.getProjectStore().createAndSaveProject("prototype2", "test57", customer,
                typo, sector, date, 7, 5000);
        proj2 = company.getProjectStore().createAndSaveProject("prototype2", "test58", customer,
                typo, sector, date, 7, 5000);
        proj3 = company.getProjectStore().createAndSaveProject("prototype3", "test59", customer,
                typo, sector, date, 7, 5000);
        projectList = company.getProjectStore();
    }

/*    @Test
    @DisplayName("Add Resource")
    public void addResource() {
        //Arrange
        //User
        SystemUser newUser = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "Qwerty_1",
                "Qwerty_1", "photo.png", userProfile.getUserProfileId());
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        Resource resAllo2 = new Resource(newUser, startDateToAllocate, endDateToAllocate, new CostPerHour(100), new PercentageOfAllocation(.2));
        //Act
        boolean result = proj.addResource(resAllo2);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Get Team Member By Index Test")
    public void getTeamMemberByIndex() {
        //Arrange
        SystemUser newUser = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, new CostPerHour(100), new PercentageOfAllocation(.5));
        proj1.addResource(resAllo1);
        proj3.addResource(resAllo1);
        //Act
        Resource result = proj1.getTeamMemberByIndex(0);
        //Assert
        assertEquals(resAllo1, result);
    }

    @Test
    @DisplayName("Validate Allocation True")
    public void validateAllocationTrue() {
        //Arrange
        //User
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, new CostPerHour(100), new PercentageOfAllocation(.2));
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        //Save Project and Add Resource to Project
        proj1.addResource(resAllo1);
        proj3.addResource(resAllo1);
        //Act
        boolean result = projectList.validateAllocation(newUser, 0.2, startDateToAllocate, endDateToAllocate);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Validate Allocation False")
    public void validateAllocationFalse() {
        //Arrange
        //User
        SystemUser newUser = new SystemUser("xyz", "fase@beaver.com", "des", "Qwerty_1", "Qwerty_1", "photo.png", userProfile.getUserProfileId());
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, new CostPerHour(100), new PercentageOfAllocation(.2));
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        //Save Project and Add Resource to Project
        proj1.addResource(resAllo1);
        proj3.addResource(resAllo1);
        //Act
        boolean result = projectList.validateAllocation(newUser, 0.7, startDateToAllocate, endDateToAllocate);
        //Assert
        assertFalse(result);
    }

 */
}
