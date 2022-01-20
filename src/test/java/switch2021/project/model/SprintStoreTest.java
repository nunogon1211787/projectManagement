package switch2021.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SprintStoreTest {

    @Test
    @DisplayName("Teste para verificação da criação do sprint")
    public void createSprintSuccess() {

        //Arrange

        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.now();
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);


        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_1", LocalDate.now(), project.getSprintDuration());
        //Act
        String name = "Sprint_1";

        //Assert
        assertEquals(name, sprint.getName());
        assertEquals(date, sprint.getStartDate());
        assertEquals(LocalDate.of(2022, 2, 3), sprint.getEndDate());

    }

    @Test
    @DisplayName("Teste para verificação da criação do sprint - erro")
    public void createSprintFail() {

        //Arrange

        Company company = new Company();

        Project project;

        LocalDate date = LocalDate.of(2022, 01, 01);
        company.getBusinessSectorStore().addBusinessSector(company.getBusinessSectorStore().createBusinessSector("sector"));
        company.getCustomerStore().add(company.getCustomerStore().createCustomer("Teste", "Teste"));

        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        project = company.getProjectStore().createProject("123testcode", "prototype", "test1234", customer,
                typo, sector, date, 7, 5000);
        project.setSprintDuration(2);


        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_1", LocalDate.now(), project.getSprintDuration());
        //Act
        String name = "Sprint_2";

        //Assert
        assertNotEquals(name, sprint.getName());
        assertNotEquals(date, sprint.getStartDate());
        assertNotEquals(LocalDate.of(2022, 4, 3), sprint.getEndDate());

    }


//    @Test
//    @DisplayName("Teste para verificação da criação de 2 sprint com a mesma informação.")
//    public void createEqualsSprint() {
//
//        //Arrange
//        SprintStore sprintStore1 = new SprintStore();
//        Sprint sprint1 = sprintStore1.createSprint("Sprint_0", LocalDate.now(), 2);
//        //Act
//        Sprint sprint2 = sprintStore1.createSprint("Sprint_0", LocalDate.now(), 2);
//        //Assert
//        assertEquals(sprint1, sprint2);
//
//    }

    @Test
    @DisplayName("Teste de pesquisa do sprint pelo id")
    public void catchSprintByID() {

        //Arrange
        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_0", LocalDate.now(), 2);
        //Act
        sprintStore.addSprint(sprint);
        //Assert
        assertEquals(sprint, sprintStore.getSprint(1));

    }

    @Test
    @DisplayName("Teste de Validação da Existência do Sprint")
    public void validateSprintExists() {

        //Arrange
        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("Sprint_0", LocalDate.now(), 2);
        //Act
        sprintStore.addSprint(sprint);
        //Assert
        assertTrue(sprintStore.validateIfSprintAlreadyExists(sprint));
    }


    @Test
    @DisplayName("Teste para validação da start date")
    public void validationOfStarDate_0() {
        //Assert
        SprintStore sprintStore = new SprintStore();
        Sprint sprint0 = sprintStore.createSprint("Sprint_1", LocalDate.now(), 2);
        sprint0.setEndDate(LocalDate.of(2022, 01, 21));
        sprintStore.addSprint(sprint0);

        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Sprint sprint = sprintStore.createSprint("Sprint_0", LocalDate.now(), 2);
        });
    }

    @Test
    @DisplayName("Teste para validação da start date")
    public void validationOfStarDate() {
        //Assert
        SprintStore sprintStore = new SprintStore();
        Sprint sprint0 = sprintStore.createSprint("Sprint_1", LocalDate.now(), 2);
        sprint0.setEndDate(LocalDate.now());
        sprintStore.addSprint(sprint0);

        assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Sprint sprint = sprintStore.createSprint("Sprint_0", LocalDate.now(), 2);
        });
    }


    @Test
    @DisplayName("Teste para salvar um sprint")
    public void checkSavetheSprint () {

       //Arrange
       SprintStore sprintStore = new SprintStore();
       Sprint sprint = sprintStore.createSprint("String_0", LocalDate.now(), 2);
       //Act and Assert
        assertTrue(sprintStore.saveSprint(sprint));

    }

    @Test
    @DisplayName("Teste para salvar um sprint")
    public void checkIfSprintWasSaved() {

        //Arrange
        SprintStore sprintStore = new SprintStore();
        Sprint sprint = sprintStore.createSprint("String_0", LocalDate.now(), 2);
        //Act
        sprintStore.saveSprint(sprint);
        // Assert
        assertEquals(sprint, sprintStore.getSprint(1));

    }

}
