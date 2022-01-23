package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ViewStatusOfActivitiesInAProjectControllerTest {

    @Test
    void getListOfProjectActivities() {


        Company company = new Company();
        ProjectTeamTest projectTeamTest = new ProjectTeamTest();
        Typology typo = company.getTypologyStore().getTypology("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        ProjectStatus projectStatus = new ProjectStatus("ToStart");
        Project project2 = new Project("1234testcode", "prototype", "test56", customer,
                typo, sector, LocalDate.now(), projectStatus, 7, 5000);

        company.getProjectStore().addProject(project2);

        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());
        Task taskTest = sprint1.getTaskstore().createTask("test");
        Task taskTest2 = sprint1.getTaskstore().createTask("test2");
        sprint1.getTaskstore().addTaskToTheList(taskTest);
        List<Task> taskList = new ArrayList<>();

        project2.getSprintStore().saveSprint(sprint1);
        taskList.add(taskTest);
        taskList.add(taskTest2);

        List<Task> listTest = new ArrayList<>();

        ViewStatusOfActivitiesInAProjectController viewStatusTester = new ViewStatusOfActivitiesInAProjectController(company);

        viewStatusTester.getProjectByCode("1234testcode");
        listTest = viewStatusTester.getListOfProjectActivities();

        assertEquals(listTest,project2.getSprintStore().getListOfAllAActivitiesOfAProject());

    }
}