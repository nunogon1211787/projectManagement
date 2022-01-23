package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Sprint;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintStore;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

public class CreateSprintController {
    private Company company;
    private Project project;
    private ProjectStore projectStore;
    private SprintStore sprintStore;
    private Sprint sprint;

    public CreateSprintController(Company company) {

        this.company = company;
        this.project = null;
        this.projectStore = null;
        this.sprintStore = null;
        this.sprint = null;
    }

    public List<Project> getCurrentProjectListByUserEmail(String email) {
        ProjectStore projectstore = company.getProjectStore();
        return projectstore.getCurrentProjectListByUserEmail(email);
    }

    public Project getProject(Company company, String code) {
        return this.project = company.getProjectStore().getProjectByCode(code);
    }

    public Sprint createSprint(String name, LocalDate startDate) {
        return this.sprint = this.project.getSprintStore().createSprint(name, startDate,this.project.getSprintDuration());
    }

    public boolean saveSprint(Sprint sprint) {
        return this.project.getSprintStore().saveSprint(this.sprint);
    }
}


