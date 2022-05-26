package switch2021.project.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switch2021.project.datamodel.ProjectJpa;
import switch2021.project.dto.ProjectDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectControllerTest {

    @Autowired
    ProjectController ctrl;

    @Test
    void createProject() {
    }

    @Test
    void testCreateProject() {

        ProjectDTO test = new ProjectDTO();

        test.projectName = "Project 2";
        test.description = "Isto é um projecto test";
        test.businessSector = "IT";
        test.typology = "costfix";
        test.customer = "Internal";
        test.startDate = "2022-05-27";
        test.endDate = "2022-06-27";
        test.numberOfSprints = "1";
        test.budget = "1000";
        test.sprintDuration = "7";

        ctrl.createProject(test);


    }
}