package switch2021.project.controller;

import switch2021.project.dto.StartASprintDTO;
import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.model.Sprint.SprintID;
import switch2021.project.model.Sprint.SprintStore;
import switch2021.project.repositories.ProjectTeam;
import switch2021.project.repositories.ProjectStore;

import java.time.LocalDate;

public class StartASprintController {

    /**
     * Attributes
     **/

    private final Company company;


    /**
     * Constructor to test (without SINGLETON)
     **/

    public StartASprintController(Company company) {
        this.company = company;
    }

    /**
     * Method
     **/

    public boolean startASprint(StartASprintDTO sprintDTO) {

        String code = sprintDTO.getProjectCode();
        ProjectStore projectStore = company.getProjectStore();
        Project proj = projectStore.getProjectByCode(code);
        SprintID sprintID = sprintDTO.getSprintID();
        LocalDate startDate = sprintDTO.getStartDate();
        ProjectTeam projectTeam = proj.getProjectTeam();
        int sprintDuration = proj.getSprintDuration().getSprintDurationDays();
        SprintStore sprintList = proj.getSprintList();
        return sprintList.startASprint(sprintID, startDate, projectTeam, sprintDuration);

    }
}

