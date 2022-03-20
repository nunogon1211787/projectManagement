package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SystemUserStore;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AssociateResourceControllerTest {

    @Test
    @DisplayName("Constructor Test - AssociateResourceController")
    public void associateResourceController() {
        //Arrange
        //Company
        Company comTest = new Company();
        //Project
        LocalDate startProjectDate = LocalDate.of(2021, 2, 25);
        Customer cust = new Customer("ght@gmail.com", "Name", 123456789);
        Typology typo = new Typology("typo1");
        BusinessSector busSector = new BusinessSector("busSec1");
        Project proj1 = comTest.getProjectStore().createProject("gfd", "ghjsasd", cust, typo, busSector, startProjectDate, 30, 4500);
        comTest.getProjectStore().saveNewProject(proj1);
        //User
        UserProfile pro = comTest.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@gmail.com", "description", "Qwerty_1", "Qwerty_1", "", pro);
        comTest.getSystemUserStore().saveSystemUser(newUser);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        //Act
        AssociateResourceController controllerTest = new AssociateResourceController(comTest);
        boolean result = controllerTest.associateResource("fase@gmail.com", "Project_2022_1", startDateToAllocate, endDateToAllocate, 100, .2);
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to get the Project List with Success")
    void getProjectListSuccess() {
        //Arrange
        Company company = new Company();
        AssociateResourceController controller = new AssociateResourceController(company);
        ProjectStore projectStore = company.getProjectStore();
        //Project1
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("isep");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("it");
        Project project1 = projectStore.createProject("prototype4", "proj4Prototype", customer,
                typo, sector, LocalDate.of(2021, 12, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2021, 12, 31));
        projectStore.saveNewProject(project1);
        //Project2
        Project project2 = projectStore.createProject("prototype3", "proj3Prototype", customer,
                typo, sector, LocalDate.of(2022, 1, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2022, 1, 31));
        projectStore.saveNewProject(project2);
        //Project3
        Project project3 = projectStore.createProject("prototype2", "proj2Prototype", customer,
                typo, sector, LocalDate.of(2021, 12, 1), 2, 4000);
        project1.setEndDate(LocalDate.of(2021, 12, 31));
        projectStore.saveNewProject(project3);
        //Act
        List<Project> projects = controller.getProjectList();
        //Assert
        assertEquals(3,projects.size());
        assertTrue(projects.contains(project1));
        assertTrue(projects.contains(project2));
        assertTrue(projects.contains(project3));
    }
    @Test
    @DisplayName("Test to get the SystemUser List with Success")
    void getSystemUserListSuccess() {
        //Arrange
        Company company = new Company();
        AssociateResourceController controller = new AssociateResourceController(company);
        SystemUserStore systemUserStore = company.getSystemUserStore();
        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = systemUserStore.createSystemUser("manuelmartins", "manuelmartins@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        systemUserStore.saveSystemUser(user);
        SystemUser user2 = systemUserStore.createSystemUser("manuel", "manuel@beaver.com", "tester", "Qwerty_1", "Qwerty_1", "photo", profile);
        systemUserStore.saveSystemUser(user2);
        //Act
        List<SystemUser> users = controller.getSystemUserList();
        //Assert
        assertEquals(2,users.size());
        assertTrue(users.contains(user));
        assertTrue(users.contains(user2));
    }

    @Test
    @DisplayName("Constructor Test (fail) - AssociateResourceController")
    public void associateResourceControllerFail() {
        //Arrange
        //Company
        Company comTest = new Company();
        //Project
        LocalDate startProjectDate = LocalDate.of(2021, 2, 25);
        Customer cust = new Customer("ght@gmail.com", "Name", 123456789);
        Typology typo = new Typology("typo1");
        BusinessSector busSector = new BusinessSector("busSec1");
        Project proj1 = comTest.getProjectStore().createProject("gfd", "ghjsasd", cust, typo, busSector, startProjectDate, 30, 4500);
        Project proj2 = comTest.getProjectStore().createProject("ghj", "testtestest", cust, typo, busSector, startProjectDate, 30, 4500);
        comTest.getProjectStore().saveNewProject(proj1);
        comTest.getProjectStore().saveNewProject(proj2);
        //User
        UserProfile pro = comTest.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@gmail.com", "description", "Qwerty_1", "Qwerty_1", "", pro);
        comTest.getSystemUserStore().saveSystemUser(newUser);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);
        //Controller
        AssociateResourceController controllerTest = new AssociateResourceController(comTest);
        controllerTest.associateResource("fase@gmail.com", "Project_2022_1", startDateToAllocate, endDateToAllocate, 100, .6);
        //Act
        boolean result = controllerTest.associateResource("fase@gmail.com", "Project_2022_2", startDateToAllocate, endDateToAllocate, 100, .5);
        //Assert
        assertFalse(result);
    }
}