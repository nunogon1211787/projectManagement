package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductBacklogControllerTest {

    @Test

    public void getAllProjectListByUserEmail (){
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);
        Project project2 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project2);

        UserProfile userProfile = new UserProfile("zzz");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        project.addResource(input);
        project2.addResource(input);

        // Act
        ProductBacklogController productBacklogController = new ProductBacklogController(company);
        List<Project> projectList = productBacklogController.getAllProjectListByUserEmail("cris@ipp.pt");
        // Assert
        assertEquals(2, projectList.size());

    }
    @Test

    public void getAllProjectListByUserEmailIsBlank (){
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);
        Project project2 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project2);

        UserProfile userProfile = new UserProfile("zzz");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        project.addResource(input);
        project2.addResource(input);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        ProductBacklogController productBacklogController = new ProductBacklogController(company);
        List<Project> projectList = productBacklogController.getAllProjectListByUserEmail("");
        });
        // Assert
        assertEquals("Email cannot be blank", exception.getMessage());

    }

    @Test

    public void getAllProjectListByUserEmailDontExist (){
        //Arrange
        Company company = new Company();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        Project project = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project);
        Project project2 = company.getProjectStore().createProject("123testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), 7, 5000);
        company.getProjectStore().addProject(project2);

        UserProfile userProfile = new UserProfile("zzz");
        SystemUser newUser = new SystemUser("xyz", "cris@ipp.pt", "des", "gth", "gth", "", userProfile);
        LocalDate startDate = LocalDate.of(2021, 12, 31);
        LocalDate endDate = LocalDate.of(2022, 1, 5);
        Resource input = new Resource(newUser, startDate, endDate, 100, .5);
        project.addResource(input);
        project2.addResource(input);

        // Act
            ProductBacklogController productBacklogController = new ProductBacklogController(company);
            List<Project> projectList = productBacklogController.getAllProjectListByUserEmail("dani@ipp.pt");

        // Assert
        assertEquals(0, projectList.size());


    }
}
