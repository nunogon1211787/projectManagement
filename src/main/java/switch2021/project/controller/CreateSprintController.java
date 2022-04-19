package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.repositories.ProjectStore;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CreateSprintController {

    /**
     * Attributes
     **/
    private final Company company;
    private Project proj;
    private ProjectStore projectStore;


    /**
     * Constructor to test (without SINGLETON)
     **/
    public CreateSprintController(Company company) {
        this.company = company;
    }


    /**
     * Methods
     **/
    public List<Project> getCurrentProjectListByUserEmail(String email) {
        this.projectStore = company.getProjectStore();
        List<Project> currentProjectListByUser = projectStore.getCurrentProjectsByUserEmail(email);
        return Collections.unmodifiableList(currentProjectListByUser);
    }

    public Project getProject(String code) {
        this.proj = this.projectStore.getProjectByCode(code);
        return this.proj;
    }

    public boolean createAndSaveSprint(int projectID, String sprintID, String name) {
        int sprintDuration = this.proj.getSprintDuration().getSprintDurationDays();
        this.proj.getSprintList().createAndSaveSprint(projectID, sprintID, name, sprintDuration);
        return true;
    }
}


