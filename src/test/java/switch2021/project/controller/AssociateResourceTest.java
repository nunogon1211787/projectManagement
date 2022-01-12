package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AssociateResourceTest {

    @Test
    @DisplayName("Teste construtor de AssociateResourceController")
    public void associateResourceController() {
        //Arrange
        /** Company **/
        Company comTest = new Company();
        /** Project **/
        //List<Project> testProjectList = comTest.getArrayProj();
        LocalDate startProjectDate = LocalDate.of(2021, 02, 25);
        Customer cust = new Customer("ght@gmail.com");
        Typology typo = new Typology("typo1");
        BusinessSector busSector = new BusinessSector("busSec1");
        Project proj1 = comTest.getProjectStore().createProject("1", "gfd", "ghj", cust, typo, busSector, startProjectDate, 30, 4500);

        /** user **/
        UserProfile pro = new UserProfile("mku", "sss");
        SystemUser newUser = new SystemUser("xyz", "fase@gmail.com", "des", "gth","gth","", pro);
        LocalDate startDateToAllocate = LocalDate.of(2021, 12, 13);
        LocalDate endDateToAllocate = LocalDate.of(2021, 12, 14);

        /** Construtor Controller **/
        AssociateResourceController controllerTest = new AssociateResourceController(comTest, proj1);
        boolean result = controllerTest.associateResource("fase@gmail.com", "1", startDateToAllocate, endDateToAllocate, 100, .2);

        assertTrue(result);
    }
}