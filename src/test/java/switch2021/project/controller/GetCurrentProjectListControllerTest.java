package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import switch2021.project.model.*;
import switch2021.project.stores.ProjectStore;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class GetCurrentProjectListControllerTest {
    @Test
    public void getCurrentProjectListByUserEmailSucess() {
        //Arrange
        GetCurrentProjectListController controller = new GetCurrentProjectListController();
        Company company = controller.getCompany();
        ProjectStore projStore = company.getProjectStore();
        ProjectTeamTest test = new ProjectTeamTest();

        projStore.addProject(test.getProj1());
        projStore.addProject(test.getProj2());
        projStore.addProject(test.getProj3());
        projStore.addProject(test.getCurrentProject());

        // Act
        List<Project> projectList = controller.getCurrentProjectListByUserEmail("manuelmartins@beaver.com");
        int sizeExpected = projectList.size();
        // Assert
        assertEquals(1, sizeExpected);
    }
}
