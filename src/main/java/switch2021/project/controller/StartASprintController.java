package switch2021.project.controller;

import switch2021.project.dto.StartASprintDTO;
import switch2021.project.model.Company;
import switch2021.project.model.Project;
import switch2021.project.model.ProjectTeam;
import switch2021.project.stores.ProjectStore;
import switch2021.project.stores.SprintList;

import java.time.LocalDate;

public class StartASprintController {

    /**
     * Attributes
     **/

    private final Company company;


    /**
     * Constructor to UI (with SINGLETON)
     **/

//    public StartASprintController() {
//        this.company = App.getInstance().getCompany();
//    }

    /**
     * Constructor to test (without SINGLETON)
     **/

    public StartASprintController(Company company) {
        this.company = company;
    }

    /**
     * Methods
     **/

    public boolean startASprint(StartASprintDTO sprintDTO) {

        String code = sprintDTO.getProjectCode();
        ProjectStore projectStore = company.getProjectStore();
        Project proj = projectStore.getProjectByCode(code);
        int sprintID = sprintDTO.getSprintID();
        LocalDate startDate = sprintDTO.getStartDate();
        ProjectTeam projectTeam = proj.getProjectTeam();
        int sprintDuration = proj.getSprintDuration();
        SprintList sprintList = new SprintList();
        return sprintList.startASprint(sprintID, startDate, projectTeam, sprintDuration);

    }
}

