package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Project.Sprint;
import switch2021.project.stores.ProjectStore;

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

    public Sprint createSprint(String name, LocalDate startDate) {
        int sprintDuration = this.proj.getSprintDuration();
        return this.proj.getSprintList().createSprint(name, startDate, sprintDuration);
    }

    public boolean saveSprint(Sprint sprint) {
        return this.proj.getSprintList().saveSprint(sprint);
    }

}


