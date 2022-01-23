package switch2021.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SprintStoreTest {

    private Company company;
    private ProjectStore projectStore;
    private Project project;
    private SprintStore sprintStore;
    LocalDate date;
    private Sprint sprint;
    private Sprint sprint2;
    private Sprint sprint3;
    private Sprint sprint4;
    private Sprint sprint5;
    private Sprint sprint6;
    private Sprint sprint7;
    private Sprint sprint8;
    private Sprint sprint9;
    private Sprint sprint10;


    @BeforeEach
    public void initialize(){
        company = new Company();
        projectStore = company.getProjectStore();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        date = LocalDate.of(2022, 1, 1);
        project = projectStore.createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);
        sprintStore = new SprintStore();
        sprint = sprintStore.createSprint("Sprint_1", LocalDate.of(2022, 1, 1), project.getSprintDuration());
        sprint2 = sprintStore.createSprint("Sprint_2", LocalDate.of(2022, 1, 16), project.getSprintDuration());
        sprint3 = sprintStore.createSprint("Sprint_3", LocalDate.of(2021, 12, 19), project.getSprintDuration());
        sprint4 = sprintStore.createSprint("Sprint_4", LocalDate.of(2021, 12, 5), project.getSprintDuration());
        sprint5 = sprintStore.createSprint("Sprint_5", LocalDate.of(2022, 1, 30), project.getSprintDuration());
        sprint6 = sprintStore.createSprint("Sprint_6", LocalDate.of(2022, 2, 13), project.getSprintDuration());
        sprint7 = sprintStore.createSprint("Sprint_7", LocalDate.of(2022, 2, 27), project.getSprintDuration());
        sprint8 = sprintStore.createSprint("Sprint_8", LocalDate.now().minusWeeks(2), project.getSprintDuration());
        sprint9 = sprintStore.createSprint("Sprint_9", LocalDate.now(), project.getSprintDuration());
        sprint10 = sprintStore.createSprint("Sprint_10", LocalDate.now().plusWeeks(2), project.getSprintDuration());
        sprintStore.saveSprint(sprint);
        sprintStore.saveSprint(sprint2);
        sprintStore.saveSprint(sprint3);
        sprintStore.saveSprint(sprint4);
        sprintStore.saveSprint(sprint5);
        sprintStore.saveSprint(sprint6);
        sprintStore.saveSprint(sprint7);
        sprintStore.saveSprint(sprint8);
        sprintStore.saveSprint(sprint9);
        sprintStore.saveSprint(sprint10);

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with success")
    public void createSprintSuccess() {
        //Act
        String name = "Sprint_1";
        //Assert
        assertEquals(name, sprint.getName());
        assertEquals(date, sprint.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 14), sprint.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with name failure")
    public void createSprintFail_Name() {
        //Act
        String name = "Sprint_2";

        //Assert
        assertNotEquals(name, sprint.getName());
        assertEquals(date, sprint.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 14), sprint.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with start date failure")
    public void createSprintFail_StartDate() {
        //Arrange
        SprintStore sprintStoreTest = new SprintStore();
        Sprint sprintTest = sprintStoreTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 2 ), project.getSprintDuration());
        //Act
        String name = "Sprint_1";

        //Assert
        assertEquals(name, sprintTest.getName());
        assertNotEquals(date, sprintTest.getStartDate());
        assertEquals(LocalDate.of(2022, 1, 15), sprintTest.getEndDate());

    }

    @Test
    @DisplayName("Verification Test, to create a Sprint with sprint duration failure")
    public void createSprintFail_SprintDuration() {
        //Arrange
        SprintStore sprintStoreTest = new SprintStore();
        Sprint sprintTest = sprintStoreTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 1 ), project.getSprintDuration());
        //Act
        String name = "Sprint_1";

        //Assert
        assertEquals(name, sprintTest.getName());
        assertEquals(date, sprintTest.getStartDate());
        assertNotEquals(LocalDate.of(2022, 1, 16), sprintTest.getEndDate());

    }


    @Test
    @DisplayName("Verification Test, to create a Sprint with failure (all parameters)")
    public void createSprintFailAll() {
        //Arrange
        SprintStore sprintStoreTest = new SprintStore();
        Sprint sprintTest = sprintStoreTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 2 ), project.getSprintDuration());
        //Act
        String name = "Sprint_2";

        //Assert
        assertNotEquals(name, sprintTest.getName());
        assertNotEquals(date, sprintTest.getStartDate());
        assertNotEquals(LocalDate.of(2022, 2, 16), sprintTest.getEndDate());

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
        SprintStore sprintStoreTest = new SprintStore();
        Sprint sprintTest = sprintStoreTest.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintStoreTest.saveSprint(sprintTest);
        //Assert
        assertEquals(sprintTest, sprintStoreTest.getSprint(1));

    }

    @Test
    @DisplayName("Test to verify if sprint already exists")
    public void validateSprintExists() {

        //Arrange
        SprintStore sprintStoreTest = new SprintStore();
        Sprint sprintTest = sprintStoreTest.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintStoreTest.saveSprint(sprintTest);
        //Assert
        assertTrue(sprintStoreTest.validateIfSprintAlreadyExists(sprintTest));
    }


    @Test
    @DisplayName("Test to verify if it is caught the exception of having a sprint with a start date that is " +
            "before the end date of another sprint")
    public void validationOfStarDate() {
        //Assert
        SprintStore sprintStoreTest = new SprintStore();
        Sprint sprintTest = sprintStoreTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 1), 2);
        sprintTest.setEndDate(LocalDate.of(2022, 1, 20));
        sprintStoreTest.saveSprint(sprintTest);

        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            sprintStoreTest.createSprint("Sprint_0", LocalDate.of(2022, 1, 10), 2);
        });
    }


    @Test
    @DisplayName("Test to verify if the act of saving a sprint is correct")
    public void checkSavetheSprint () {

       //Arrange
       SprintStore sprintStoreTest = new SprintStore();
       Sprint sprintTest = sprintStoreTest.createSprint("String_0", LocalDate.of(2022, 1, 1), 2);
       //Act and Assert
        assertTrue(sprintStoreTest.saveSprint(sprintTest));

    }

    @Test
    @DisplayName("Test to verify if the sprint was saved")
    public void checkIfSprintWasSaved() {

        //Arrange
        SprintStore sprintStoreTest = new SprintStore();
        Sprint sprintTest = sprintStoreTest.createSprint("String_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintStoreTest.saveSprint(sprintTest);
        // Assert
        assertEquals(sprintTest, sprintStoreTest.getSprint(1));

    }

    @Test
    @DisplayName("Get the next start Sprint")
    public void getCurrentNextSprintTest() {
        //Arrange
        Sprint sprintTest = sprintStore.getNextSprint();
        //Assert
        assertEquals(sprint5,sprintTest);
    }

//    @Test
//    @DisplayName("Get the next start Sprint")
//    public void getCurrentNextSprintTestNull() {
//        //Arrange
//
//        //
//        Sprint sprintTest = sprintStore.getNextSprint();
//        //Assert
//        assertEquals(null,sprintTest);
//    }

}
