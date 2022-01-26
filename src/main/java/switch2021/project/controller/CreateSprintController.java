package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Sprint;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintList;
import switch2021.project.utils.App;

import java.time.LocalDate;
import java.util.List;

public class CreateSprintController {
    private Company company;
    private Project proj;
    private ProjectStore projectStore;
    private SprintList sprintList;
    private Sprint sprint;
    private List<Project> currentProjectListByUser;

    /**
     * Constructor to UI (with SINGLETON).
     */
    public CreateSprintController() {
        this.company = App.getInstance().getCompany();
    }

    /**
     * Constructor to test (without SINGLETON).
     */
    public CreateSprintController(Company company) {
        this.company = company;
    }


    public List<Project> getCurrentProjectListByUserEmail(String email) {
        this.projectStore = company.getProjectStore();
        this.currentProjectListByUser = projectStore.getCurrentProjectListByUserEmail(email);
        return this.currentProjectListByUser;
    }

    public Project getProject(String code) {
        this.proj = this.projectStore.getProjectByCode(code);
        return this.proj;
    }

    public Sprint createSprint(String name, LocalDate startDate) {
        this.sprintList = this.proj.getSprintList();
        int sprintDuration = this.proj.getSprintDuration();

        this.sprint = sprintList.createSprint(name, startDate, sprintDuration);
        return this.sprint;
    }

    public boolean saveSprint() {
        return this.sprintList.saveSprint(this.sprint);
    }
}


