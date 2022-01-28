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
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        ProjectStatus projectStatus = new ProjectStatus("ToStart");
        Project project2 = new Project("Project_test", "prototype",  customer,
                typo, sector, LocalDate.now(), projectStatus, 7, 5000);

        company.getProjectStore().saveNewProject(project2);

        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());
        Task taskTest = sprint1.getTaskList().createTask("test");
        Task taskTest2 = sprint1.getTaskList().createTask("test2");
        sprint1.getTaskList().addTaskToTheList(taskTest);
        List<Task> taskList = new ArrayList<>();

        project2.getSprintList().saveSprint(sprint1);
        taskList.add(taskTest);
        taskList.add(taskTest2);

        List<Task> listTest = new ArrayList<>();

        ViewStatusOfActivitiesInAProjectController viewStatusTester = new ViewStatusOfActivitiesInAProjectController(company);

        viewStatusTester.getProjectByCode("Project_2022_1");
        listTest = viewStatusTester.getListOfProjectActivities();

        assertEquals(listTest,project2.getSprintList().getListOfAllAActivitiesOfAProject());

    }
}