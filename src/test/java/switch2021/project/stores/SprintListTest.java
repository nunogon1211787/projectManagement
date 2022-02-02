package switch2021.project.stores;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class SprintListTest {

    private Company company;
    private ProjectStore projectStore;
    private Project project;
    private SprintList sprintList;
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
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().saveNewCustomer(company.getCustomerStore().createCustomer("Teste", "Teste"));
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");
        date = LocalDate.of(2022, 1, 1);
        project = projectStore.createProject( "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);
        sprintList = new SprintList();
        sprint = sprintList.createSprint("Sprint_1", LocalDate.of(2022, 1, 1), project.getSprintDuration());
        sprint2 = sprintList.createSprint("Sprint_2", LocalDate.of(2022, 1, 16), project.getSprintDuration());
        sprint3 = sprintList.createSprint("Sprint_3", LocalDate.of(2021, 12, 19), project.getSprintDuration());
        sprint4 = sprintList.createSprint("Sprint_4", LocalDate.of(2021, 12, 5), project.getSprintDuration());
        sprint5 = sprintList.createSprint("Sprint_5", LocalDate.of(2022, 1, 30), project.getSprintDuration());
        sprint6 = sprintList.createSprint("Sprint_6", LocalDate.of(2022, 2, 13), project.getSprintDuration());
        sprint7 = sprintList.createSprint("Sprint_7", LocalDate.of(2022, 2, 27), project.getSprintDuration());
        sprint8 = sprintList.createSprint("Sprint_8", LocalDate.now().minusWeeks(2), project.getSprintDuration());
        sprint9 = sprintList.createSprint("Sprint_9", LocalDate.now(), project.getSprintDuration());
        sprint10 = sprintList.createSprint("Sprint_10", LocalDate.now().plusWeeks(2), project.getSprintDuration());
        sprintList.saveSprint(sprint);
        sprintList.saveSprint(sprint2);
        sprintList.saveSprint(sprint3);
        sprintList.saveSprint(sprint4);
        sprintList.saveSprint(sprint5);
        sprintList.saveSprint(sprint6);
        sprintList.saveSprint(sprint7);
        sprintList.saveSprint(sprint8);
        sprintList.saveSprint(sprint9);
        sprintList.saveSprint(sprint10);

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
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 2 ), project.getSprintDuration());
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
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 1 ), project.getSprintDuration());
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
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 2 ), project.getSprintDuration());
        //Act
        String name = "Sprint_2";

        //Assert
        assertNotEquals(name, sprintTest.getName());
        assertNotEquals(date, sprintTest.getStartDate());
        assertNotEquals(LocalDate.of(2022, 2, 16), sprintTest.getEndDate());

    }


    @Test
    @DisplayName("Test to verify the creation of 2 sprints with the same information")
    public void createEqualsSprint() {

        //Arrange
        SprintList sprintStore1 = new SprintList();
        Sprint sprint1 = sprintStore1.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        Sprint sprint2 = sprintStore1.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Assert
        assertEquals(sprint1, sprint2);

    }

    @Test
    @DisplayName("Test to search sprint by id")
    public void catchSprintByID() {

        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintListTest.saveSprint(sprintTest);
        //Assert
        assertEquals(sprintTest, sprintListTest.getSprint(1));

    }

    @Test
    @DisplayName("Test to verify if sprint already exists")
    public void validateSprintExists() {

        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintListTest.saveSprint(sprintTest);
        //Assert
        assertTrue(sprintListTest.validateIfSprintAlreadyExists(sprintTest));
    }


    @Test
    @DisplayName("Test to verify if it is caught the exception of having a sprint with a start date that is " +
            "before the end date of another sprint")
    public void validationOfStarDate() {
        //Assert
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("Sprint_1", LocalDate.of(2022, 1, 1), 2);
        sprintTest.setEndDate(LocalDate.of(2022, 1, 20));
        sprintListTest.saveSprint(sprintTest);
        assertNull(sprintListTest.createSprint("Sprint_0", LocalDate.of(2022, 1, 10), 2));
    }


    @Test
    @DisplayName("Test to verify if the act of saving a sprint is correct")
    public void checkSaveTheSprint () {

       //Arrange
       SprintList sprintListTest = new SprintList();
       Sprint sprintTest = sprintListTest.createSprint("String_0", LocalDate.of(2022, 1, 1), 2);
       //Act and Assert
        assertTrue(sprintListTest.saveSprint(sprintTest));

    }

    @Test
    @DisplayName("Test to verify if the sprint was saved")
    public void checkIfSprintWasSaved() {

        //Arrange
        SprintList sprintListTest = new SprintList();
        Sprint sprintTest = sprintListTest.createSprint("String_0", LocalDate.of(2022, 1, 1), 2);
        //Act
        sprintListTest.saveSprint(sprintTest);
        // Assert
        assertEquals(sprintTest, sprintListTest.getSprint(1));

    }

    @Test
    @DisplayName("Get the next start Sprint")
    public void getCurrentSprintTest() {
        //Arrange
        SprintList storeTest = new SprintList();
        storeTest.saveSprint(sprint8);
        storeTest.saveSprint(sprint9);
        storeTest.saveSprint(sprint10);
        //Act
        Sprint sprintTest = storeTest.getCurrentSprint();
        //Assert
        assertEquals(sprint9,sprintTest);
    }

    @Test
    @DisplayName("Get NullPointerException for the next Sprint")
    public void getNullCurrentSprintTest() {
        //Assert
        assertThrows(NullPointerException.class, () -> {
            //Arrange
            SprintList storeTest = new SprintList();
            storeTest.saveSprint(sprint);
            storeTest.saveSprint(sprint3);
            storeTest.saveSprint(sprint4);
            //Act
            storeTest.getCurrentSprint();
        });
    }
}
