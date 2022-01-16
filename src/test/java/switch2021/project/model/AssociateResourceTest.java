package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.controller.AssociateResourceController;
import switch2021.project.utils.App;

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
        company = new Company(); // sempre a mesma instancia
        userProfile = company.getUserProfileStore().getUserProfile("Visitor");

        LocalDate date = LocalDate.of(2021, 12, 12);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        proj = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, date, 7, 5000);
        proj1 = company.getProjectStore().createProject("345testcode", "prototype2", "test57", customer,
                typo, sector, date, 7, 5000);
        proj2 = company.getProjectStore().createProject("987testcode", "prototype2", "test58", customer,
                typo, sector, date, 7, 5000);
        proj3 = company.getProjectStore().createProject("678testcode", "prototype3", "test59", customer,
                typo, sector, date, 7, 5000);
        projectList = company.getProjectStore();
    }

    @Test
    @DisplayName("Teste add Resource")
    public void addResource() {
        //Arrange
        //** user **//
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
    @DisplayName("Teste getTeamMemberByIndex")
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
    @DisplayName("Teste getTeamMemberByIndex")
    public void createResource() {
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
//        /** user **/

        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth", "img_xyz", userProfile);
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .2);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        Resource resAllo2 = new Resource(newUser, startDateToAllocate, endDateToAllocate, 100, .2);

        /** project list **/
        projectList.addProject(proj1);
        projectList.addProject(proj2);
        projectList.addProject(proj3);
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
//        /** user **/
        SystemUser newUser = new SystemUser("xyz", "fase", "des", "gth", "gth", "img_xyz", userProfile);
        LocalDate startDateAllocated = LocalDate.of(2021, 12, 12);
        LocalDate endDateAllocated = LocalDate.of(2021, 12, 24);

        Resource resAllo1 = new Resource(newUser, startDateAllocated, endDateAllocated, 100, .2);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        Resource resAllo2 = new Resource(newUser, startDateToAllocate, endDateToAllocate, 100, .2);

        /** project list **/
        projectList.addProject(proj1);
        projectList.addProject(proj2);
        projectList.addProject(proj3);
        proj1.addResource(resAllo1);
        proj3.addResource(resAllo1);

        //Act
        boolean result = projectList.validateAllocation(newUser,0.7, startDateToAllocate, endDateToAllocate);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Teste construtor de AssociateResourceController")
    public void associateResourceController() {

        //Arrange
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        SystemUser newUser = new SystemUser("Ivan Aguiar", "xxxx@isep.ipp.pt", "tester", "123456",
                "123456", "img_123456", userProfile);

//      Construtor AssociateResource Controller
        AssociateResourceController controllerTest = new AssociateResourceController(company, proj1);
        boolean result = controllerTest.associateResource("xxxx@isep.ipp.pt", "123testcode", startDateToAllocate, endDateToAllocate, 100, .2);

        assertTrue(result);
    }
}
