package switch2021.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2021.project.model.Company;
import switch2021.project.stores.ProjectStore;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSprintControllerTest {


    @Test
    @DisplayName("Controller Test, to get a list of projects")
    public void getProjectList() {

        //Arrange
        Company company = new Company();
        CreateSprintController controllerTest1 = new CreateSprintController();
        ProjectStore projectStore = company.getProjectStore();
        projectStore.getProjectList();
        //Act
        CreateSprintController controllerTest2 = new CreateSprintController();
        ProjectStore projectStore1 = company.getProjectStore();
        projectStore1.getProjectList();
        //Assert
        assertEquals(projectStore, projectStore1);
    }

}
