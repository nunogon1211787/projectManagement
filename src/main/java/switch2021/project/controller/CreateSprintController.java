package switch2021.project.controller;

import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.Sprint;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintStore;

import java.time.LocalDate;

public class CreateSprintController {

    private Company company;
    private Project project;
    private Sprint sprint;
    private SprintStore sprintStore;

    public CreateSprintController (Company company, Project project, SprintStore sprintStore, Sprint sprint) {

        this.company = company;
        this.project = project;
        this.sprintStore = sprintStore;
        this.sprint = sprint;
    }

    public ProjectStore getProjectStore () {

        return this.company.getProjectStore();
    }

    //Fazer a validação de se a startdate do sprint esta´contida das datas do projecto. Assim terá de "impedir" a criação do sprint, caso aconteça.
    //validação do nr de sprints.
    //public SprintStore createSprint (String name, LocalDate startDate) {

    //    this.sprintStore.createSprint(name, startDate);

    //}
}
