package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AssociateResourceTest {

    private Company company;
    private UserProfile userProfile;
    private Project proj;
    private Project proj1;
    private Project proj2;
    private Project proj3;
    private ProjectStore projectList;

    @BeforeEach
    public void init() {
        company = new Company();
        userProfile = company.getUserProfileStore().getUserProfile("Visitor");

        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste", 123456789));

        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        proj = company.getProjectStore().createProject( "prototype", "test56", customer,
                typo, sector, date, 7, 5000);
        proj1 = company.getProjectStore().createProject( "prototype2", "test57", customer,
                typo, sector, date, 7, 5000);
        proj2 = company.getProjectStore().createProject( "prototype2", "test58", customer,
                typo, sector, date, 7, 5000);
        proj3 = company.getProjectStore().createProject( "prototype3", "test59", customer,
                typo, sector, date, 7, 5000);
        projectList = company.getProjectStore();
    }

    @Test
    @DisplayName("Add Resource")
    public void addResource() {
        //Arrange
        //User
        SystemUser newUser = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456",
                "123456", "img_123456", userProfile);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        Resource resAllo2 = new Resource(newUser, startDateToAllocate, endDateToAllocate, 100, .2);
        //Act
        boolean result = proj.addResource(resAllo2);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Get Team Member By Index Test")
    public void getTeamMemberByIndex() {
        //Arrange
        SystemUser newUser = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456", "123456", "img_123456", userProfile);
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .5);
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
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth", "img_xyz", userProfile);
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .2);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        //Save Project and Add Resource to Project
        projectList.saveNewProject(proj1);
        projectList.saveNewProject(proj2);
        projectList.saveNewProject(proj3);
        proj1.addResource(resAllo1);
        proj3.addResource(resAllo1);
        //Act
        boolean result = projectList.validateAllocation(newUser,0.2, startDateToAllocate, endDateToAllocate);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Validate Allocation False")
    public void validateAllocationFalse() {
        //Arrange
        //User
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth", "img_xyz", userProfile);
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);
        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .2);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        //Save Project and Add Resource to Project
        projectList.saveNewProject(proj1);
        projectList.saveNewProject(proj2);
        projectList.saveNewProject(proj3);
        proj1.addResource(resAllo1);
        proj3.addResource(resAllo1);
        //Act
        boolean result = projectList.validateAllocation(newUser,0.7, startDateToAllocate, endDateToAllocate);
        //Assert
        assertFalse(result);
    }
}
