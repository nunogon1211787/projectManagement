package switch2021.project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssociateResourceTest {

    @Test
    @DisplayName("Teste construtor de AssociateResourceController")
    public void associateResourceController() {
        //Arrange
            //Company
        Company comTest = new Company();
            //Project
        //List<Project> testProjectList = comTest.getArrayProj();
        LocalDate startProjectDate = LocalDate.of(2021, 2, 25);
        Customer cust = new Customer("ght@gmail.com","Name");
        Typology typo = new Typology("typo1");
        BusinessSector busSector = new BusinessSector("busSec1");
        Project proj1 = comTest.getProjectStore().createProject( "gfd", "ghjsasd", cust, typo, busSector, startProjectDate, 30, 4500);
        comTest.getProjectStore().saveNewProject(proj1);

            //user
        UserProfile pro = comTest.getUserProfileStore().getUserProfile("Visitor");
        SystemUser newUser = new SystemUser("xyz", "fase@gmail.com", "description", "gth","gth","", pro);
        comTest.getSystemUserStore().saveSystemUser(newUser);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);

            //Construtor Controller
        AssociateResourceController controllerTest = new AssociateResourceController(comTest);
        boolean result = controllerTest.associateResource("fase@gmail.com", "1", startDateToAllocate, endDateToAllocate, 100, .2);

        assertTrue(result);
    }
}