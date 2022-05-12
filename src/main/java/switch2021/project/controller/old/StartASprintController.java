package switch2021.project.controller.old;

import switch2021.project.dto.old.StartASprintDTO;
import switch2021.project.model.Company;
import switch2021.project.model.Project.Project;
import switch2021.project.repositories.SprintRepository;
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
        Project proj = projectStore.findById(code);
        String sprintID = sprintDTO.getSprintID().toString();
        LocalDate startDate = sprintDTO.getStartDate();
        ProjectTeam projectTeam = proj.getProjectTeam();
        int sprintDuration = proj.getSprintDuration().getSprintDurationDays();
        SprintRepository sprintList = proj.getSprintList();
        return sprintList.startASprint(sprintID, startDate, projectTeam, sprintDuration);

    }
}

