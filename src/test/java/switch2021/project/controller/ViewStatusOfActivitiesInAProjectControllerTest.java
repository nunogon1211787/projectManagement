package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ViewStatusOfActivitiesInAProjectControllerTest {

    @Test
    void getListOfProjectActivities() {


        Company company = new Company();
        ProjectTeamTest projectTeamTest = new ProjectTeamTest();
        Typology typo = company.getTypologyStore().getTypologyByDescription("Fixed Cost");
        Customer customer = company.getCustomerStore().getCustomerByName("Teste");
        BusinessSector sector = company.getBusinessSectorStore().getBusinessSectorByDescription("sector");

        ProjectStatus projectStatus = new ProjectStatus("ToStart");
        Project project2 = new Project("Project_test", "prototype", customer,
                typo, sector, LocalDate.now(), projectStatus, 7, 5000);

        company.getProjectStore().saveNewProject(project2);

        Sprint sprint1 = new Sprint("Effort View", LocalDate.now());

        UserProfile profile = company.getUserProfileStore().getUserProfile("Visitor");
        SystemUser user = company.getSystemUserStore().createSystemUser("manuelbras", "manuelbras@beaver.com", "tester", "ghi", "ghi", "photo", profile);
        LocalDate startDateMb = LocalDate.of(2022, 1, 1);
        LocalDate endDateMb = LocalDate.of(2022, 1, 31);
        Resource resource = new Resource(user, startDateMb, endDateMb, 100, .5);
        String taskDescription = "must be at least 20 characters";
        TaskType taskType = company.getTaskTypeStore().getTypeByName("Testing");

        Task taskTest = sprint1.getTaskList().createTask("test", taskDescription, 8.0, taskType, resource);
        Task taskTest2 = sprint1.getTaskList().createTask("test2", taskDescription, 8.0, taskType, resource);
        sprint1.getTaskList().saveTask(taskTest);
        List<Task> taskList = new ArrayList<>();

        project2.getSprints().saveSprint(sprint1);
        taskList.add(taskTest);
        taskList.add(taskTest2);

        List<Task> listTest = new ArrayList<>();

        ViewStatusOfActivitiesInAProjectController viewStatusTester = new ViewStatusOfActivitiesInAProjectController(company);

        viewStatusTester.getProjectByCode("Project_2022_1");
        listTest = viewStatusTester.getListOfProjectActivities();

        assertEquals(listTest,project2.getSprints().getListOfAllAActivitiesOfAProject());

    }
}