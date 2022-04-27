package switch2021.project.controller.old;

import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.Sprint;
import switch2021.project.repositories.ProjectStore;

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
        this.proj = this.projectStore.findById(code);
        return this.proj;
    }

    public boolean createAndSaveSprint(String projectID, String sprintID, String name) {
        Sprint sprint = this.proj.getSprintList().createSprint(projectID, sprintID, name);
        this.proj.getSprintList().saveSprint(sprint);
        return true;

    }
}


