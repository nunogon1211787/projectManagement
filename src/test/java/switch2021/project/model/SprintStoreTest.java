package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SprintStoreTest {

    @Test
    @DisplayName("Verification Test, to create a Sprint with success")
    public void createSprintSuccess() {

        //Arrange

        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.of(2022, 1, 1);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);

        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_1", LocalDate.of(2022, 1, 1), project.getSprintDuration());
        //Act
        String name = "Sprint_1";

        //Assert
        assertEquals(name, sprint.getName());
        assertEquals(date, sprint.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 15), sprint.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with name failure")
    public void createSprintFail_Name() {

        //Arrange

        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.of(2022, 1, 1);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);


        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_1", LocalDate.of(2022, 1, 1 ), project.getSprintDuration());
        //Act
        String name = "Sprint_2";

        //Assert
        assertNotEquals(name, sprint.getName());
        assertEquals(date, sprint.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 15), sprint.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with start date failure")
    public void createSprintFail_StartDate() {

        //Arrange

        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.of(2022, 1, 1);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);


        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_1", LocalDate.of(2022, 1, 2 ), project.getSprintDuration());
        //Act
        String name = "Sprint_1";

        //Assert
        assertEquals(name, sprint.getName());
        assertNotEquals(date, sprint.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 16), sprint.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with sprint duration failure")
    public void createSprintFail_SprintDuration() {

        //Arrange

        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.of(2022, 1, 1);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);


        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_1", LocalDate.of(2022, 1, 1 ), project.getSprintDuration());
        //Act
        String name = "Sprint_1";

        //Assert
        assertEquals(name, sprint.getName());
        assertEquals(date, sprint.getStartDate());
        assertNotEquals(LocalDate.of(2022, 1, 16), sprint.getEndDate());

    }


    @Test
    @DisplayName("Verification Test, to create a Sprint with failure (all parameters)")
    public void createSprintFailAll() {

        //Arrange

        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.of(2022, 1, 1);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);


        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_1", LocalDate.of(2022, 1, 2 ), project.getSprintDuration());
        //Act
        String name = "Sprint_2";

        //Assert
        assertNotEquals(name, sprint.getName());
        assertNotEquals(date, sprint.getStartDate());
        assertNotEquals(LocalDate.of(2022, 2, 16), sprint.getEndDate());

    }


//    @Test
//    @DisplayName("Test to verify the creation of 2 sprints with the same information")
//    public void createEqualsSprint() {
//
//        //Arrange
//        SprintStore sprintStore1 = new SprintStore();
//        Sprint sprint1 = sprintStore1.createSprint("Sprint_0", LocalDate.of(2022, 01, 01), 2);
//        //Act
//        Sprint sprint2 = sprintStore1.createSprint("Sprint_0", LocalDate.of(2022, 01, 01), 2);
//        //Assert
//        assertEquals(sprint1, sprint2);
//
//    }

    @Test
    @DisplayName("Test to search sprint by id")
    public void catchSprintByID() {

        //Arrange
        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintStore.saveSprint(sprint);
        //Assert
        assertEquals(sprint, sprintStore.getSprint(1));

    }

    @Test
    @DisplayName("Test to verify if sprint already exists")
    public void validateSprintExists() {

        //Arrange
        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintStore.saveSprint(sprint);
        //Assert
        assertTrue(sprintStore.validateIfSprintAlreadyExists(sprint));
    }


    @Test
    @DisplayName("Test to verify if it is caught the exception of having a sprint with a start date that is " +
            "before the end date of another sprint")
    public void validationOfStarDate() {
        //Assert
        SprintStore sprintStore = new SprintStore();
        Sprint sprint0 = sprintStore.createSprint("Sprint_1", LocalDate.of(2022, 1, 1), 2);
        sprint0.setEndDate(LocalDate.of(2022, 1, 20));
        sprintStore.saveSprint(sprint0);

        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Sprint sprint = sprintStore.createSprint("Sprint_0", LocalDate.of(2022, 1, 10), 2);
        });
    }


    @Test
    @DisplayName("Test to verify if the act of saving a sprint is correct")
    public void checkSavetheSprint () {

       //Arrange
       SprintStore sprintStore = new SprintStore();
       Sprint sprint = sprintStore.createSprint("String_0", LocalDate.of(2022, 1, 1), 2);
       //Act and Assert
        assertTrue(sprintStore.saveSprint(sprint));

    }

    @Test
    @DisplayName("Test to verify if the sprint was saved")
    public void checkIfSprintWasSaved() {

        //Arrange
        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("String_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintStore.saveSprint(sprint);
        // Assert
        assertEquals(sprint, sprintStore.getSprint(1));

    }


}
