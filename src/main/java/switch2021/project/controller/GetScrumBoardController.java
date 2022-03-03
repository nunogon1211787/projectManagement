package switch2021.project.controller;

import switch2021.project.dto.UserStoryStatusDTO;
import switch2021.project.mapper.ScrumBoardMapper;
import switch2021.project.model.*;

import java.util.List;

public class GetScrumBoardController {

    /**
     * Attributes
     **/

    private final Company company;
    private final ScrumBoardMapper mapper;
    private  Sprint sprint;
    private Project project;
    private SprintBacklog sprintBacklog;
    private List<UserStory> userStoryList;

    /**
     * Constructor to test (without SINGLETON)
     **/

    public GetScrumBoardController(Company company, ScrumBoardMapper mapper) {
        this.company = company;
        this.mapper = mapper;
    }

    /**
     *Getter's
     */

    public Project getProject(String projectCode) {
        return project = company.getProjectStore().getProjectByCode(projectCode);
    }

    public Sprint getCurrentSprint() {
        return sprint = project.getCurrentSprint();
    }

    public SprintBacklog getSprintBacklog() {
        return sprintBacklog = sprint.getSprintBacklog();
    }

    public List<UserStory> getUserStoryList() {
        return userStoryList = sprintBacklog.getUserStoryList();
    }

    /**
     * Method
     **/

    public List<UserStoryStatusDTO> getScrumBoard(){
        return this.mapper.toDtoList(userStoryList);
    }

    //Direct Method
   /* public List<ScrumBoardDTO> getScrumBoard2(String projectCode) {
        List<UserStory> list = company.getProjectStore().getProjectByCode(projectCode).getSprints().getCurrentSprint().getSprintBacklog().getUserStoryList();
        return this.mapper.toDtoList(list);
    }*/
}
