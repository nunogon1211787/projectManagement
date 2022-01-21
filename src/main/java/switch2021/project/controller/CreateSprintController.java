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

    public CreateSprintController() {

        this.company = App.getInstance().getCompany();
        this.project = null;
        this.projectStore = null;
        this.sprintStore = null;
        this.sprint = null;
    }

    public List<Project> getProjectList() {
        ProjectStore projectstore = company.getProjectStore();
        return projectstore.getProjectList();
    }

    public Project getProject(String code) {
        return this.company.getProjectStore().getProjectByCode(code);
    }

    public Sprint createSprint(String name, LocalDate startDate) {
        SprintStore sprintStore = this.project.getSprintStore();
        return sprintStore.createSprint(name, startDate,this.project.getSprintDuration());
    }

    public boolean saveSprint() {
        return this.project.getSprintStore().saveSprint(this.sprint);
    }
}








    //Fazer a validação de se a startdate do sprint esta´contida das datas do projecto.
    //validação do nr de sprints (se o sprint atual está contido no nr de sprints do projeto).

